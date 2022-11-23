package com.trifcdr.githubdownloader.domain.repository

import com.trifcdr.githubdownloader.data.database.model.Download
import com.trifcdr.githubdownloader.domain.model.*

/**
 * Created by trifcdr.
 */
interface GitHubRepository {

    suspend fun getUser(userParams: UserParams) : UsersList

    suspend fun getRepository(user: GitHubUser) : ReposList

    suspend fun downloadRepository(repo: GitHubRepo)

    suspend fun getAllDownloads() : MutableList<Download>

    suspend fun insertDownload(download: Download)

    suspend fun deleteDownload(download: Download)
}