package com.trifcdr.githubdownloader.presentation.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trifcdr.githubdownloader.R
import com.trifcdr.githubdownloader.data.database.model.Download
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
        requireActivity().addMenuProvider(object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {}
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                findNavController().navigate(R.id.usersFragment)
                return true
            }

        })
        vm = (activity as MainActivity).vm
        initRecyclerView()
        (vm as MainViewModel).resultLiveRepos.observe(viewLifecycleOwner){
            adapter.setReposList(it.repositories)
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
        rv.layoutManager = LinearLayoutManager(
            this.context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = RepositoryAdapter()
        rv.adapter = adapter
    }


}