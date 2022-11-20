package com.trifcdr.githubdownloader.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.trifcdr.githubdownloader.databinding.UserItemBinding
import com.trifcdr.githubdownloader.domain.model.GitHubUser

/**
 * Created by trifcdr.
 */
class UserAdapter : RecyclerView.Adapter<UserAdapter.UserHolder>() {

    private var users = mutableListOf<GitHubUser>()

    private var mCallback: ((result: GitHubUser) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        return UserHolder(
            UserItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val ghUser = users[position]
        holder.binding.usernameTv.text = ghUser.login
        Glide.with(holder.binding.root).load(ghUser.avatar_url).into(holder.binding.avatar)
        holder.binding.root.setOnClickListener {
            mCallback?.invoke(ghUser)
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setUserList(list: MutableList<GitHubUser>){
        users = list
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(function: ((GitHubUser) -> Unit)?){
        mCallback = function
    }

    inner class UserHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root)
}