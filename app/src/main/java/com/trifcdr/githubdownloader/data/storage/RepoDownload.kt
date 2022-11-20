package com.trifcdr.githubdownloader.data.storage

import android.provider.ContactsContract.CommonDataKinds.StructuredName
import com.trifcdr.githubdownloader.domain.model.GitHubRepo

/**
 * Created by trifcdr.
 */
interface RepoDownload {

    suspend fun downloadRepository(repo: GitHubRepo)
}