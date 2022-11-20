package com.trifcdr.githubdownloader.domain.usecase

import com.trifcdr.githubdownloader.domain.model.GitHubRepo
import com.trifcdr.githubdownloader.domain.repository.GitHubRepository

/**
 * Created by trifcdr.
 */
class DownloadRepositoryUseCase(private val gitHubRepository: GitHubRepository) {

    suspend fun execute(repository: GitHubRepo){
        gitHubRepository.downloadRepository(repository)
    }
}