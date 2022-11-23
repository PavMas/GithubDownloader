package com.trifcdr.githubdownloader.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.trifcdr.githubdownloader.databinding.FragmentDownloadsBinding
import com.trifcdr.githubdownloader.presentation.MainViewModel
import com.trifcdr.githubdownloader.presentation.adapter.DownloadsAdapter

/**
 * Created by trifcdr.
 */
class DownloadsFragment : Fragment() {

    private lateinit var vm: ViewModel

    private lateinit var rv: RecyclerView

    private lateinit var binding: FragmentDownloadsBinding

    private lateinit var adapter: DownloadsAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDownloadsBinding.inflate(inflater, container, false)
        initRecyclerView()
        vm = ViewModelProviders.of(requireActivity())[MainViewModel::class.java]
        (vm as MainViewModel).resultLiveDownloads.observe(viewLifecycleOwner) {
            adapter.setDownloadsList(it)
        }
        (vm as MainViewModel).getAllDownloads()
        adapter.setOnItemClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(it.name)
                .setMessage("Do you want to delete this repository from list?")
                .setNegativeButton("Close"
                ) { _, _ ->  }
                .setPositiveButton("Yes"
                ) { _, _ -> (vm as MainViewModel).deleteDownload(it)
                    (vm as MainViewModel).getAllDownloads()

                }
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