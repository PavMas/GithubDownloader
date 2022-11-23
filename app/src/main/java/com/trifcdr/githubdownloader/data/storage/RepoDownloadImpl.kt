package com.trifcdr.githubdownloader.data.storage

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import com.trifcdr.githubdownloader.data.rest.BASE_URL
import com.trifcdr.githubdownloader.domain.model.GitHubRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by trifcdr.
 */
class RepoDownloadImpl(private val context: Context) : RepoDownload {

    override suspend fun downloadRepository(repo: GitHubRepo) {

        CoroutineScope(Dispatchers.IO).launch {
            val request =
                DownloadManager.Request(Uri.parse("$BASE_URL/repos/${repo.owner.login}/${repo.name}/zipball"))
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "GithubDownloader/${repo.name}")
            val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            manager.enqueue(request)
        }
    }
}