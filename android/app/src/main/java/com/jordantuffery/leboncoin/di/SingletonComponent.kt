package com.jordantuffery.leboncoin.di

import com.jordantuffery.leboncoin.album.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class])
interface SingletonComponent {
    fun inject(activity: MainActivity)
}