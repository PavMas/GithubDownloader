package com.trifcdr.githubdownloader.data.database

import com.trifcdr.githubdownloader.data.database.model.Download

/**
 * Created by trifcdr.
 */
interface DownloadsDB {

    suspend fun getAll() : MutableList<Download>

    suspend fun insertDownload(download: Download)

    suspend fun deleteDownload(download: Download)
}