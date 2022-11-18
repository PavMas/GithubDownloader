package com.trifcdr.githubdownloader.domain.model

/**
 * Created by trifcdr.
 */
data class GitHubUser(val login: String,
                      val id: Long,
                      val avatar_url: String) : java.io.Serializable