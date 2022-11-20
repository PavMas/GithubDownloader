package com.trifcdr.githubdownloader.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trifcdr.githubdownloader.databinding.FragmentRepositoryBinding
import com.trifcdr.githubdownloader.presentation.MainActivity
import com.trifcdr.githubdownloader.presentation.MainViewModel
import com.trifcdr.githubdownloader.presentation.adapter.RepositoryAdapter

/**
 * Created by trifcdr.
 */
@SuppressLint("ResourceType")
class RepositoriesFragment : Fragment() {

    private val args: RepositoriesFragmentArgs by navArgs()

    private lateinit var binding: FragmentRepositoryBinding

    lateinit var rv: RecyclerView

    private lateinit var adapter: RepositoryAdapter

    private lateinit var vm: ViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepositoryBinding.inflate(layoutInflater, container, false)
        val user = args.user
        vm = (activity as MainActivity).vm
        initRecyclerView()
        (vm as MainViewModel).resultLiveRepos.observe(viewLifecycleOwner){
            adapter.setReposList(it.repositories)
        }
        (vm as MainViewModel).getRepositories(user = user)
        adapter.setOnDownloadClickListener {
            (vm as MainViewModel).downloadRepository(it)
        }
        return binding.root
    }

    private fun initRecyclerView(){
        rv = binding.rvRepos
        setManagerAndAdapter()
    }
    private fun setManagerAndAdapter() {
        rv.layoutManager = LinearLayoutManager(
            this.context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = RepositoryAdapter()
        rv.adapter = adapter
    }
}