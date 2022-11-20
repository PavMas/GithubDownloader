package com.trifcdr.githubdownloader.domain.usecase

import com.trifcdr.githubdownloader.data.database.model.Download
import com.trifcdr.githubdownloader.domain.repository.GitHubRepository

/**
 * Created by trifcdr.
 */
class GetAllDownloadsUseCase(private val gitHubRepository: GitHubRepository) {

    suspend fun execute() : MutableList<Download>{
        return gitHubRepository.getAllDownloads()
    }
}