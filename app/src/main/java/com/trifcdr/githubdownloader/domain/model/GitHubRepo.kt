package com.trifcdr.githubdownloader.domain.model

/**
 * Created by trifcdr.
 */
data class GitHubRepo(val id: Long,
                      val name: String,
                      val owner: GitHubUser,
                      val description: String,
                      val language: String)