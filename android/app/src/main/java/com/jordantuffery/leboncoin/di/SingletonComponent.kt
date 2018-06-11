package com.jordantuffery.leboncoin.di

import com.jordantuffery.leboncoin.presentation.base.BaseFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class])
interface SingletonComponent {
    fun inject(fragment: BaseFragment)
}