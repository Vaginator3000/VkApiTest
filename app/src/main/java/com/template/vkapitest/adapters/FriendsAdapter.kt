package com.template.vkapitest.adapters

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.template.vkapitest.R
import com.template.vkapitest.models.FriendModel
import kotlinx.android.synthetic.main.cell_friend.view.*
import java.util.ArrayList

class FriendsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mFriendsList = ArrayList<FriendModel>()
    private var mSourceList = ArrayList<FriendModel>()

    fun setupFriends(friendsList: ArrayList<FriendModel>) {
        mSourceList.clear()
        mSourceList = friendsList
        filter(query = "")
    }

    fun filter(query: String) {
        mFriendsList.clear()
        mSourceList.forEach {
            if (it.name.contains(query, ignoreCase = true) || it.surname.contains(query, ignoreCase = true)) {
                mFriendsList.add(it)
            }
            else {
                it.city?.let { city ->
                    if (city.contains(query, ignoreCase = true)) {
                        mFriendsList.add(it)
                    }
                }
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.cell_friend, parent, false)

        return FriendsViewHolder(itemView = itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is FriendsViewHolder) {
            holder.bind(mFriendsList[position])
        }
    }

    override fun getItemCount() =
        mFriendsList.size

    class FriendsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(friendModel: FriendModel) {
            itemView.friend_tv_username.text = "${friendModel.name} ${friendModel.surname}"

            friendModel.avatar?.let { url ->
                Picasso.get().load(url)
                    .into(itemView.friend_civ_avatar)
            }

            friendModel.city?.let { city ->
                itemView.friend_tv_city.text = city
                itemView.friend_tv_city.visibility = View.VISIBLE
            }

            if (friendModel.isOnline)
                itemView.friend_img_online.visibility = View.VISIBLE
            else
                itemView.friend_img_online.visibility = View.GONE
        }

    }
}