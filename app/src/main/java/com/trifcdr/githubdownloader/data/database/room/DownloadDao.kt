package com.trifcdr.githubdownloader.data.database.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.trifcdr.githubdownloader.data.database.model.Download

/**
 * Created by trifcdr.
 */

@Dao
interface DownloadDao {

    @Query("SELECT * FROM Download")
    fun getAll() : MutableList<Download>

    @Insert
    fun insert(download: Download)

}