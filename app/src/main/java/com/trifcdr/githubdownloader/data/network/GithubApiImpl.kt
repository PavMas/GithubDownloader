package com.trifcdr.githubdownloader.data.network

import com.trifcdr.githubdownloader.data.network.model.ReposListResponse
import com.trifcdr.githubdownloader.data.network.model.UsersListResponse
import com.trifcdr.githubdownloader.data.network.retrofit.GithubApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by trifcdr.
 */
class GithubApiImpl : GithubApi {

    private val api by lazy { GithubApiService.getInstance() }

    override suspend fun getUsers(username: String): UsersListResponse {
        var responseFromServer = UsersListResponse(0, true, mutableListOf())
        val response = api.getUsers(username = username)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userResponse = response.execute()
                responseFromServer = userResponse.body()!!
            } catch (_: Exception) {
            }
        }.join()
        return responseFromServer
    }

    override suspend fun getRepos(username: String): ReposListResponse {
        val responseFromServer = ReposListResponse(mutableListOf())
        val response = api.getRepos(name = username)
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val reposResponse = response.execute()
                responseFromServer.reposList.addAll(reposResponse.body()!!)
            }
            catch (_: Exception){
            }
        }.join()
        return responseFromServer
    }
}