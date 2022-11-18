package com.trifcdr.githubdownloader.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trifcdr.githubdownloader.data.network.GithubApiImpl
import com.trifcdr.githubdownloader.data.repository.GitHubRepositoryImpl
import com.trifcdr.githubdownloader.domain.usecase.GetRepositoryByUsername
import com.trifcdr.githubdownloader.domain.usecase.GetUsersUseCase

/**
 * Created by trifcdr.
 */
class MainViewModuleFactory : ViewModelProvider.Factory {

    private val gitHubRepository by lazy(LazyThreadSafetyMode.NONE) { GitHubRepositoryImpl(GithubApiImpl()) }

    private val getUsersUseCase by lazy(LazyThreadSafetyMode.NONE) { GetUsersUseCase(gitHubRepository = gitHubRepository) }

    private val getRepositoryByUsername by lazy(LazyThreadSafetyMode.NONE) { GetRepositoryByUsername(gitHubRepository = gitHubRepository) }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(getUsersUseCase = getUsersUseCase) as T
    }
}