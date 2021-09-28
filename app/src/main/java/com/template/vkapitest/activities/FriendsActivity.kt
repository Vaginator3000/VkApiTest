package com.template.vkapitest.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.template.vkapitest.R
import com.template.vkapitest.adapters.FriendsAdapter
import com.template.vkapitest.models.FriendModel
import com.template.vkapitest.presenters.FriendsPresenter
import com.template.vkapitest.views.FriendsView
import kotlinx.android.synthetic.main.activity_friends.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class FriendsActivity : MvpAppCompatActivity(), FriendsView {

    @InjectPresenter
    lateinit var friendsPresenter: FriendsPresenter

    val friendsAdapter = FriendsAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

        friendsPresenter.loadFriends()

        setRecyclerViewParams()

        friends_search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                friendsAdapter.filter(query = s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }

    private fun setRecyclerViewParams() {
        rv_friends.adapter = friendsAdapter
        rv_friends.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        rv_friends.setHasFixedSize(true)
    }

    override fun startLoading() {
        txt_no_items.visibility = View.GONE
        rv_friends.visibility = View.GONE
        cpv_friends.visibility = View.VISIBLE
    }

    override fun endLoading() {
        cpv_friends.visibility = View.GONE
    }


    override fun showError(txtResource: Int) {
        txt_no_items.text = getString(txtResource)
    }

    override fun setupEmptyList() {
        txt_no_items.visibility = View.VISIBLE
        rv_friends.visibility = View.GONE
    }

    override fun setupFriendsList(friendsList: ArrayList<FriendModel>) {
        txt_no_items.visibility = View.GONE
        rv_friends.visibility = View.VISIBLE

        friendsAdapter.setupFriends(friendsList = friendsList)
    }
}