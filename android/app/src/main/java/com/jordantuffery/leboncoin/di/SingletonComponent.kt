package com.jordantuffery.leboncoin.di

import com.jordantuffery.leboncoin.base.BaseFragment
import com.jordantuffery.leboncoin.presentation.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class])
interface SingletonComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: BaseFragment)
}