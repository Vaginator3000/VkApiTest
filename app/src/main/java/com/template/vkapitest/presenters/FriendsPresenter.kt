package com.template.vkapitest.presenters

import com.template.vkapitest.R
import com.template.vkapitest.models.FriendModel
import com.template.vkapitest.providers.FriendsProvider
import com.template.vkapitest.views.FriendsView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class FriendsPresenter: MvpPresenter<FriendsView>() {
    fun loadFriends() {
        viewState.startLoading()
        FriendsProvider(presenter = this).testLoadFriends(hasFriends = true)
    }

    fun friendsLoader(friendsList: ArrayList<FriendModel>) {
        viewState.endLoading()
        if(friendsList.isEmpty()) {
            viewState.setupEmptyList()
            viewState.showError(txtResource = R.string.frinds_no_found)
        } else
            viewState.setupFriendsList(friendsList = friendsList)
    }
}