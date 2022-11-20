package com.trifcdr.githubdownloader.domain.usecase

import com.trifcdr.githubdownloader.data.database.model.Download
import com.trifcdr.githubdownloader.domain.repository.GitHubRepository

/**
 * Created by trifcdr.
 */
class InsertDownloadUseCase(private val gitHubRepository: GitHubRepository) {


    suspend fun execute(download: Download){
        gitHubRepository.insertDownload(download = download)
    }
}