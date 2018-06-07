package com.jordantuffery.leboncoin.base

interface BaseContract {
    interface BaseView {
        fun showProgress()
        fun hideProgress()
        fun showError()
    }
}