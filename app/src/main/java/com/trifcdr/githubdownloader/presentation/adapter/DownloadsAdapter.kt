package com.trifcdr.githubdownloader.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trifcdr.githubdownloader.data.database.model.Download
import com.trifcdr.githubdownloader.databinding.DownloadItemBinding

/**
 * Created by trifcdr.
 */
class DownloadsAdapter : RecyclerView.Adapter<DownloadsAdapter.DownloadHolder>() {

    private var downloadsList = mutableListOf<Download>()

    private var mCallback: ((result: Download) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DownloadHolder {
        return DownloadHolder(DownloadItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
                )
            )
    }

    override fun onBindViewHolder(holder: DownloadHolder, position: Int) {
        val download = downloadsList[position]
        holder.binding.repoName.text = download.name
        holder.binding.userName.text = download.repoOwner
        holder.binding.root.setOnClickListener{
            mCallback?.invoke(download)
        }
    }

    override fun getItemCount(): Int {
        return downloadsList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDownloadsList(list: MutableList<Download>){
        downloadsList = list
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(function: ((Download) -> Unit)){
        mCallback = function
    }


    inner class DownloadHolder(val binding: DownloadItemBinding) : RecyclerView.ViewHolder(binding.root)

}