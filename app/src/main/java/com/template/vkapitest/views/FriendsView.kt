package com.template.vkapitest.views

import com.template.vkapitest.models.FriendModel
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import java.util.ArrayList

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface FriendsView: MvpView {
    fun startLoading()

    fun endLoading()

    fun showError(txtResource: Int)

    fun setupEmptyList()

    fun setupFriendsList(friendsList: ArrayList<FriendModel>)
}