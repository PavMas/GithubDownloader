package com.trifcdr.githubdownloader.domain.usecase

import com.trifcdr.githubdownloader.data.database.model.Download
import com.trifcdr.githubdownloader.domain.repository.GitHubRepository

/**
 * Created by trifcdr.
 */
class DeleteDownloadUseCase(private val gitHubRepository: GitHubRepository) {

    suspend fun execute(download: Download){
        gitHubRepository.deleteDownload(download = download)
    }

}