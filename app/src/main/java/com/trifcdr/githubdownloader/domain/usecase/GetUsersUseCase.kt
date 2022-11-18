package com.trifcdr.githubdownloader.domain.usecase

import com.trifcdr.githubdownloader.domain.model.UserParams
import com.trifcdr.githubdownloader.domain.model.UsersList
import com.trifcdr.githubdownloader.domain.repository.GitHubRepository

/**
 * Created by trifcdr.
 */
class GetUsersUseCase(val gitHubRepository: GitHubRepository) {


    suspend fun execute(userParams: UserParams) : UsersList{
        return gitHubRepository.getUser(userParams = userParams)
    }
}