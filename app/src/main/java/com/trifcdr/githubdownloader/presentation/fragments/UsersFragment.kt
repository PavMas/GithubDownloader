package com.trifcdr.githubdownloader.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trifcdr.githubdownloader.databinding.FragmentUsersBinding
import com.trifcdr.githubdownloader.domain.model.UserParams
import com.trifcdr.githubdownloader.presentation.MainActivity
import com.trifcdr.githubdownloader.presentation.MainViewModel
import com.trifcdr.githubdownloader.presentation.adapter.UserAdapter

/**
 * Created by trifcdr.
 */
@SuppressLint("ResourceType")
class UsersFragment : Fragment() {

    private lateinit var vm: ViewModel

    lateinit var rv: RecyclerView

    private lateinit var binding: FragmentUsersBinding

    lateinit var adapter: UserAdapter



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersBinding.inflate(layoutInflater, container, false)
        vm = ViewModelProviders.of(requireActivity())[MainViewModel::class.java]
        initRecyclerView()
        binding.findBtn.setOnClickListener{
            (vm as MainViewModel).searchUsers(UserParams(binding.username.text.toString()))
        }
        (vm as MainViewModel).resultLiveUsers.observe(viewLifecycleOwner) {
            adapter.setUserList(it.users)

        }
        adapter.setOnItemClickListener {

            val action = UsersFragmentDirections.usersFragmentToRepositoryFragment(it)

            findNavController()
                .navigate(action)
        }
        return binding.root
    }

    private fun initRecyclerView(){
        rv = binding.rvUsers
        setManagerAndAdapter()
    }
    private fun setManagerAndAdapter() {
        rv.layoutManager = LinearLayoutManager(
            this.context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = UserAdapter()
        rv.adapter = adapter
    }
}