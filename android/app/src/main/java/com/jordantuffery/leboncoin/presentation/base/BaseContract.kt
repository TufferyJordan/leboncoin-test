package com.jordantuffery.leboncoin.presentation.base

interface BaseContract {
    interface BaseView {
        fun showProgress()
        fun hideProgress()
        fun showError()
    }
}