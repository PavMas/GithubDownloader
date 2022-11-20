package com.trifcdr.githubdownloader.data.network.model

import com.trifcdr.githubdownloader.domain.model.GitHubRepo

/**
 * Created by trifcdr.
 */
data class ReposListResponse(val reposList: MutableList<GitHubRepo>)