package com.trifcdr.githubdownloader.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.trifcdr.githubdownloader.R
import com.trifcdr.githubdownloader.data.repository.GitHubRepositoryImpl
import com.trifcdr.githubdownloader.databinding.ActivityMainBinding
import com.trifcdr.githubdownloader.domain.usecase.GetRepositoryByUsername
import com.trifcdr.githubdownloader.domain.usecase.GetUsersUseCase

class MainActivity : AppCompatActivity() {


    lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vm = ViewModelProvider(this, MainViewModuleFactory())[MainViewModel::class.java]
        val bottomNavigation = binding.bottomNavigationView
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        bottomNavigation.setupWithNavController(navController = navHostFragment.navController)
    }
}