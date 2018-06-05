package com.jordantuffery.leboncoin.album

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.jordantuffery.leboncoin.R
import com.jordantuffery.leboncoin.api.Api
import com.jordantuffery.leboncoin.api.Photo
import com.jordantuffery.leboncoin.di.ApiModule
import com.jordantuffery.leboncoin.di.DaggerSingletonComponent
import kotlinx.android.synthetic.main.activity_main.test_button
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.MainView {
    private var presenter: MainPresenter? = null

    @Inject
    lateinit var api: Api

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerSingletonComponent.builder()
                .apiModule(ApiModule(this))
                .build()
                .inject(this)
        presenter = MainPresenter(api, this)
        test_button.setOnClickListener(this::onTestButtonClick)
    }

    private fun onTestButtonClick(view: View) {
        presenter?.requestPhotoList()
    }

    override fun onDestroy() {
        presenter = null
        super.onDestroy()
    }

    override fun showProgress() {
        Timber.e("show progress...")
    }

    override fun hideProgress() {
        Timber.e("hide progress...")
    }

    override fun populatePhotoList(photoList: List<Photo>) {
        Timber.e("photoList first element : ${photoList[0]}")
    }

    override fun showError(message: String?) {
        Timber.e("show error ... $message")
    }
}
