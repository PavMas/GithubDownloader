package com.trifcdr.githubdownloader.data.database

import android.content.Context
import com.trifcdr.githubdownloader.data.database.model.Download
import com.trifcdr.githubdownloader.data.database.room.DownloadsDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by trifcdr.
 */
class DownloadsDBImpl(private val context: Context) : DownloadsDB {

    private val database by lazy { DownloadsDatabase.getInstance(context = context) }

    override suspend fun getAll(): MutableList<Download> {
        var list = mutableListOf<Download>()
        CoroutineScope(Dispatchers.IO).launch {
            list = database.downloadDao().getAll()
        }.join()
        return list
    }

    override suspend fun insertDownload(download: Download) {
        CoroutineScope(Dispatchers.IO).launch {
            database.downloadDao().insert(download)
        }
    }
}