package com.jordantuffery.leboncoin.di

import android.content.Context
import android.net.ConnectivityManager
import com.jordantuffery.leboncoin.BuildConfig
import com.jordantuffery.leboncoin.api.Api
import com.jordantuffery.leboncoin.api.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule(private val context: Context) {
    @Provides
    @Singleton
    fun provideCall(): Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideNetworkService(retrofit: Retrofit): ApiService = retrofit.create(
            ApiService::class.java)

    @Provides
    @Singleton
    fun provideService(apiService: ApiService): Api = Api(
            apiService)

    private fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(createOfflineCacheInterceptor())
            .addNetworkInterceptor(createCacheInterceptor())
            .cache(createCache())
            .build()

    private fun createCache(): Cache? {
        var cache: Cache? = null
        try {
            cache = Cache(File(context.cacheDir, "http-cache"), (10 * 1024 * 1024).toLong())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return cache
    }

    /**
     * Create an OkHttpClient Interceptor use to store requests in the cache folder. The age is set at 2 minutes.
     * It's useful to improve communication speed between the application and the server.
     */
    private fun createCacheInterceptor(): Interceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())
        val cacheControl = CacheControl.Builder()
                .maxAge(2, TimeUnit.MINUTES)
                .build()

        response.newBuilder()
                .removeHeader(PRAGMA)
                .removeHeader(ACCESS_CONTROL_ALLOW_ORIGIN)
                .removeHeader(VARY)
                .removeHeader(CACHE_CONTROL)
                .header(CACHE_CONTROL, cacheControl.toString())
                .build()
    }

    /**
     * Create an OkHttpClient Interceptor use to store requests in the cache folder. The stale is set at 7 days.
     * It's for use the last cached request if network is not available.
     */
    private fun createOfflineCacheInterceptor(): Interceptor = Interceptor { chain ->
        var response = chain.request()
        val activeNetworkInfo = (context.getSystemService(
                Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting) {
            val cacheControl = CacheControl.Builder()
                    .maxStale(7, TimeUnit.DAYS)
                    .onlyIfCached()
                    .build()
            response = response.newBuilder()
                    .removeHeader(PRAGMA)
                    .removeHeader(ACCESS_CONTROL_ALLOW_ORIGIN)
                    .removeHeader(VARY)
                    .removeHeader(CACHE_CONTROL)
                    .cacheControl(cacheControl).build()
        }
        chain.proceed(response)
    }

    companion object {
        private const val PRAGMA = "Pragma"
        private const val ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin"
        private const val VARY = "Vary"
        private const val CACHE_CONTROL = "Cache-Control"
    }
}