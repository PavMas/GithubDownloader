package com.trifcdr.githubdownloader.presentation.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trifcdr.githubdownloader.data.database.model.Download
import com.trifcdr.githubdownloader.databinding.FragmentRepositoryBinding
import com.trifcdr.githubdownloader.presentation.MainViewModel
import com.trifcdr.githubdownloader.presentation.adapter.RepositoryAdapter

/**
 * Created by trifcdr.
 */
@SuppressLint("ResourceType")
class RepositoriesFragment : Fragment() {

    private val args: RepositoriesFragmentArgs by navArgs()

    private lateinit var binding: FragmentRepositoryBinding

    private lateinit var rv: RecyclerView

    private lateinit var adapter: RepositoryAdapter

    private lateinit var vm: ViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepositoryBinding.inflate(layoutInflater, container, false)
        val user = args.user
        vm = ViewModelProviders.of(requireActivity())[MainViewModel::class.java]
        initRecyclerView()
        requireActivity().onBackPressedDispatcher.addCallback(this){
            val action = RepositoriesFragmentDirections.actionRepositoriesFragmentToUsersFragment()
            findNavController().navigate(action)
        }
        (vm as MainViewModel).resultLiveRepos.observe(viewLifecycleOwner){
            binding.repositoryIndicator.visibility = View.GONE
            if(it.repositories.size != 0) {
                adapter.setReposList(it.repositories)
                rv.visibility = View.VISIBLE
            }
            else{
                binding.noRepos.visibility = View.VISIBLE
            }
        }
        (vm as MainViewModel).getRepositories(user = user)
        adapter.setOnDownloadClickListener {
            (vm as MainViewModel).downloadRepository(it)
            (vm as MainViewModel).insertDownload(Download(0, it.name, it.owner.login))
        }
        adapter.setOnBrowserClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.html_url))
            startActivity(intent)
        }
        return binding.root
    }

    private fun initRecyclerView(){
        rv = binding.rvRepos
        setManagerAndAdapter()
    }
    private fun setManagerAndAdapter() {
        rv.layoutManager = GridLayoutManager(
            this.context, 1,
            GridLayoutManager.VERTICAL,
            false
        )
        adapter = RepositoryAdapter()
        rv.adapter = adapter
    }
    override fun onResume() {
        super.onResume()
        binding.noRepos.visibility = View.GONE
        binding.repositoryIndicator.visibility = View.VISIBLE
        rv.visibility = View.INVISIBLE
    }

}