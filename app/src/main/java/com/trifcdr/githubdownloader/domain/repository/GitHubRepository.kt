package com.trifcdr.githubdownloader.domain.repository

import com.trifcdr.githubdownloader.domain.model.GitHubRepo
import com.trifcdr.githubdownloader.domain.model.GitHubUser
import com.trifcdr.githubdownloader.domain.model.UsersList
import com.trifcdr.githubdownloader.domain.model.UserParams

/**
 * Created by trifcdr.
 */
interface GitHubRepository {

    suspend fun getUser(userParams: UserParams) : UsersList

    fun getRepository(user: GitHubUser) : GitHubRepo
}