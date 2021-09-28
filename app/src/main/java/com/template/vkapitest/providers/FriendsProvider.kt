package com.template.vkapitest.providers

import android.os.Handler
import com.template.vkapitest.models.FriendModel
import com.template.vkapitest.presenters.FriendsPresenter
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.VKApiConfig
import com.vk.api.sdk.VKApiManager
import com.vk.api.sdk.auth.VKScope
import com.vk.api.sdk.exceptions.VKApiCodes
import com.vk.api.sdk.utils.VKUtils

class FriendsProvider(var presenter: FriendsPresenter) {
    fun testLoadFriends(hasFriends: Boolean) {
        Handler().postDelayed({
            val friendsList = ArrayList<FriendModel>()
            if (hasFriends) {
                val friend1 = FriendModel(name = "Alan", surname = "Alanov", city = "Barnaul",
                    avatar = null, isOnline = true)
                val friend2 = FriendModel(name = "Ben", surname = "Benov", city = "Barnaul",
                    avatar = "https://pbs.twimg.com/profile_images/789721786833313792/f_rtmYnn_400x400.jpg", isOnline = false)
                val friend3 = FriendModel(name = "Anton", surname = "Ivanov", city = null,
                    avatar = null, isOnline = false)
                friendsList.add(friend1)
                friendsList.add(friend2)
                friendsList.add(friend3)
            }
            TODO("----------- 20:00 ---------")
            presenter.friendsLoader(friendsList = friendsList)
        }, 500)
    }

    fun loadFriends(hasFriends: Boolean) {

    }
}