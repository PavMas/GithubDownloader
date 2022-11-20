package com.trifcdr.githubdownloader.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trifcdr.githubdownloader.databinding.RepositotyItemBinding
import com.trifcdr.githubdownloader.domain.model.GitHubRepo

/**
 * Created by trifcdr.
 */
class RepositoryAdapter : RecyclerView.Adapter<RepositoryAdapter.RepositoryHolder>() {

    private var repos = mutableListOf<GitHubRepo>()

    private var mCallback: ((result: GitHubRepo) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryHolder {
        return RepositoryHolder(
            RepositotyItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RepositoryHolder, position: Int) {
        val repository = repos[position]
        holder.binding.repoName.text = repository.name
        holder.binding.description.text = repository.description
        holder.binding.cardBtnDelete.setOnClickListener{
            mCallback?.invoke(repository)
        }
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setReposList(list: MutableList<GitHubRepo>){
        repos = list
        notifyDataSetChanged()
    }

    fun setOnDownloadClickListener(function: ((GitHubRepo) -> Unit)?){
        mCallback = function
    }

    inner class RepositoryHolder(val binding: RepositotyItemBinding) : RecyclerView.ViewHolder(binding.root)

}