package com.trifcdr.githubdownloader.data.repository

import com.trifcdr.githubdownloader.data.network.GithubApi
import com.trifcdr.githubdownloader.domain.model.*
import com.trifcdr.githubdownloader.domain.repository.GitHubRepository

/**
 * Created by trifcdr.
 */


class GitHubRepositoryImpl(private val githubApi: GithubApi) : GitHubRepository{



    override suspend fun getUser(userParams: UserParams): UsersList {
        return UsersList(users = githubApi.getUsers(username = userParams.username).items)
    }

    override fun getRepository(user: GitHubUser): GitHubRepo {
        TODO("Not yet implemented")
    }

}