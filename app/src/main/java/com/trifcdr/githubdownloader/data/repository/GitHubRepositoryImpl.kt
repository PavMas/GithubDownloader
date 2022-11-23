package com.trifcdr.githubdownloader.data.repository

import com.trifcdr.githubdownloader.data.database.DownloadsDB
import com.trifcdr.githubdownloader.data.database.model.Download
import com.trifcdr.githubdownloader.data.network.GithubApi
import com.trifcdr.githubdownloader.data.storage.RepoDownload
import com.trifcdr.githubdownloader.domain.model.*
import com.trifcdr.githubdownloader.domain.repository.GitHubRepository

/**
 * Created by trifcdr.
 */


class GitHubRepositoryImpl(private val githubApi: GithubApi,
private val repoDownload: RepoDownload,
private val database: DownloadsDB) : GitHubRepository{



    override suspend fun getUser(userParams: UserParams): UsersList {
        return UsersList(users = githubApi.getUsers(username = userParams.username).items)
    }

    override suspend fun getRepository(user: GitHubUser): ReposList {
        return ReposList(repositories = githubApi.getRepos(username = user.login).reposList)
    }

    override suspend fun downloadRepository(repo: GitHubRepo) {
        repoDownload.downloadRepository(repo)
    }

    override suspend fun getAllDownloads(): MutableList<Download> {
        return database.getAll()
    }

    override suspend fun insertDownload(download: Download) {
        database.insertDownload(download = download)
    }

    override suspend fun deleteDownload(download: Download) {
        database.deleteDownload(download = download)
    }

}