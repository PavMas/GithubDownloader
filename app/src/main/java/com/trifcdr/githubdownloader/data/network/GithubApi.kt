package com.trifcdr.githubdownloader.data.network

import com.trifcdr.githubdownloader.data.network.model.ReposListResponse
import com.trifcdr.githubdownloader.data.network.model.UsersListResponse

/**
 * Created by trifcdr.
 */
interface GithubApi {

     suspend fun getUsers(username: String) : UsersListResponse

     suspend fun getRepos(username: String) : ReposListResponse
}