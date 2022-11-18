package com.trifcdr.githubdownloader.data.network.model

import com.trifcdr.githubdownloader.domain.model.GitHubUser
import retrofit2.Call

/**
 * Created by trifcdr.
 */
data class UsersListResponse(val total_count: Int,
                             val incomplete_result: Boolean,
                             val items: MutableList<GitHubUser>)