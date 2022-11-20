package com.trifcdr.githubdownloader.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trifcdr.githubdownloader.data.database.DownloadsDBImpl
import com.trifcdr.githubdownloader.data.network.GithubApiImpl
import com.trifcdr.githubdownloader.data.repository.GitHubRepositoryImpl
import com.trifcdr.githubdownloader.data.storage.RepoDownloadImpl
import com.trifcdr.githubdownloader.domain.usecase.*

/**
 * Created by trifcdr.
 */
class MainViewModuleFactory(context: Context) : ViewModelProvider.Factory {

    private val gitHubRepository by lazy(LazyThreadSafetyMode.NONE) { GitHubRepositoryImpl(GithubApiImpl(),
        RepoDownloadImpl(context = context),
        DownloadsDBImpl(context = context)) }

    private val getUsersUseCase by lazy(LazyThreadSafetyMode.NONE) { GetUsersUseCase(gitHubRepository = gitHubRepository) }

    private val getRepositoryByUsername by lazy(LazyThreadSafetyMode.NONE) { GetRepositoryByUsername(gitHubRepository = gitHubRepository) }

    private val downloadRepositoryUseCase by lazy(LazyThreadSafetyMode.NONE) { DownloadRepositoryUseCase(gitHubRepository = gitHubRepository)}

    private val getAllDownloadsUseCase by lazy(LazyThreadSafetyMode.NONE) { GetAllDownloadsUseCase(gitHubRepository = gitHubRepository) }

    private val insertDownloadUseCase by lazy(LazyThreadSafetyMode.NONE) { InsertDownloadUseCase(gitHubRepository = gitHubRepository) }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            getUsersUseCase = getUsersUseCase,
            getRepositoryByUsername = getRepositoryByUsername,
            getAllDownloadsUseCase = getAllDownloadsUseCase,
            downloadRepositoryUseCase = downloadRepositoryUseCase,
            insertDownloadUseCase = insertDownloadUseCase
        ) as T
    }
}