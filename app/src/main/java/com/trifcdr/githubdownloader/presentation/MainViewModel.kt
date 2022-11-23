package com.trifcdr.githubdownloader.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trifcdr.githubdownloader.data.database.model.Download
import com.trifcdr.githubdownloader.domain.model.*
import com.trifcdr.githubdownloader.domain.usecase.*
import kotlinx.coroutines.launch

/**
 * Created by trifcdr.
 */
class MainViewModel(private val getUsersUseCase: GetUsersUseCase,
private val getRepositoryByUsername: GetRepositoryByUsername,
private val downloadRepositoryUseCase: DownloadRepositoryUseCase,
private val getAllDownloadsUseCase: GetAllDownloadsUseCase,
private val insertDownloadUseCase: InsertDownloadUseCase,
private val deleteDownloadUseCase: DeleteDownloadUseCase) : ViewModel() {

    private val resultLiveUsersMutable = MutableLiveData<UsersList>()
    val resultLiveUsers: LiveData<UsersList> = resultLiveUsersMutable

    private val resultLiveReposMutable = MutableLiveData<ReposList>()
    val resultLiveRepos: LiveData<ReposList> = resultLiveReposMutable

    private val resultLiveDownloadsMutable = MutableLiveData<MutableList<Download>>()
    val resultLiveDownloads: LiveData<MutableList<Download>> = resultLiveDownloadsMutable

    fun searchUsers(userParams: UserParams) = viewModelScope.launch{
        resultLiveUsersMutable.value = getUsersUseCase.execute(userParams)
    }
    fun getRepositories(user: GitHubUser) = viewModelScope.launch {
        resultLiveReposMutable.value = getRepositoryByUsername.execute(ghUser = user)
    }
    fun downloadRepository(repository: GitHubRepo) = viewModelScope.launch{
        downloadRepositoryUseCase.execute(repository = repository)
    }

    fun getAllDownloads() = viewModelScope.launch {
        resultLiveDownloadsMutable.value = getAllDownloadsUseCase.execute()
    }

    fun insertDownload(download: Download) = viewModelScope.launch {
        insertDownloadUseCase.execute(download = download)
    }

    fun deleteDownload(download: Download) = viewModelScope.launch {
        deleteDownloadUseCase.execute(download = download)
    }
}