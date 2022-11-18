package com.trifcdr.githubdownloader.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.trifcdr.githubdownloader.R
import com.trifcdr.githubdownloader.databinding.PerositoryFragmentBinding

/**
 * Created by trifcdr.
 */
@SuppressLint("ResourceType")
class RepositoriesFragment : Fragment() {

    private val args: RepositoriesFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = PerositoryFragmentBinding.inflate(layoutInflater, container, false)
        val user = args.user
        return binding.root
    }
}