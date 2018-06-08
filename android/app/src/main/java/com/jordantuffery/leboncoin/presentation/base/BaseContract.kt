package com.jordantuffery.leboncoin.presentation.base

interface BaseContract {

    // Each views of this application should manage at least those three functions
    interface BaseView {
        fun showProgress()
        fun hideProgress()
        fun showError()
    }
}