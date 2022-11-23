package com.trifcdr.githubdownloader.presentation.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.trifcdr.githubdownloader.databinding.FragmentDownloadsBinding
import com.trifcdr.githubdownloader.presentation.MainActivity
import com.trifcdr.githubdownloader.presentation.MainViewModel
import com.trifcdr.githubdownloader.presentation.adapter.DownloadsAdapter
import com.trifcdr.githubdownloader.presentation.adapter.RepositoryAdapter
import com.trifcdr.githubdownloader.presentation.adapter.UserAdapter

/**
 * Created by trifcdr.
 */
class DownloadsFragment : Fragment() {

    private lateinit var vm: ViewModel

    lateinit var rv: RecyclerView

    private lateinit var binding: FragmentDownloadsBinding

    lateinit var adapter: DownloadsAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDownloadsBinding.inflate(inflater, container, false)
        initRecyclerView()
        vm = (activity as MainActivity).vm
        (vm as MainViewModel).resultLiveDownloads.observe(viewLifecycleOwner) {
            adapter.setDownloadsList(it)
        }
        (vm as MainViewModel).getAllDownloads()
        adapter.setOnItemClickListener {
            val dialog = MaterialAlertDialogBuilder(requireContext())
                .setTitle(it.name)
                .setMessage("Do you want to delete this repository?")
                .setNegativeButton("Close"
                ) { p0, p1 ->  }
                .setPositiveButton("Yes"
                ) { p0, p1 ->  }
                .show()
        }
        return binding.root
    }

    private fun initRecyclerView(){
        rv = binding.recyclerDwnlds
        setManagerAndAdapter()
    }
    private fun setManagerAndAdapter() {
        rv.layoutManager = LinearLayoutManager(
            this.context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = DownloadsAdapter()
        rv.adapter = adapter
    }
}