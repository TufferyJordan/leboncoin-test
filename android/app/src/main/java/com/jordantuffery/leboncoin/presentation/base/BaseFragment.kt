package com.jordantuffery.leboncoin.presentation.base

import android.content.Context
import android.support.v4.app.Fragment
import com.jordantuffery.leboncoin.api.Api
import com.jordantuffery.leboncoin.di.ApiModule
import com.jordantuffery.leboncoin.di.DaggerSingletonComponent
import javax.inject.Inject

open class BaseFragment : Fragment() {
    @Inject
    lateinit var api: Api

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerSingletonComponent.builder().apiModule(ApiModule(context)).build().inject(this)
    }
}