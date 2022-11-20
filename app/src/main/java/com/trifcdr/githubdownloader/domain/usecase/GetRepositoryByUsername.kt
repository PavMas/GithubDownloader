package com.trifcdr.githubdownloader.domain.usecase

import com.trifcdr.githubdownloader.domain.model.GitHubUser
import com.trifcdr.githubdownloader.domain.model.ReposList
import com.trifcdr.githubdownloader.domain.model.UserParams
import com.trifcdr.githubdownloader.domain.repository.GitHubRepository

/**
 * Created by trifcdr.
 */
class GetRepositoryByUsername(val gitHubRepository: GitHubRepository) {

    suspend fun execute(ghUser: GitHubUser) : ReposList{
        return gitHubRepository.getRepository(user = ghUser)
    }
}