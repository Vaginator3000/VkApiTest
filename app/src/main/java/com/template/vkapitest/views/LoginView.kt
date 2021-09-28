package com.template.vkapitest.views

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface LoginView : MvpView {
    fun startLoading()

    fun endLoading()

    fun openFriends()

    fun showError(txtRes: Int)
}