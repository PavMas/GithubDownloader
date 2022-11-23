package com.trifcdr.githubdownloader.data.storage

import com.trifcdr.githubdownloader.domain.model.GitHubRepo

/**
 * Created by trifcdr.
 */
interface RepoDownload {

    suspend fun downloadRepository(repo: GitHubRepo)
}