package com.trifcdr.githubdownloader.data.database.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import com.trifcdr.githubdownloader.data.database.model.Download

/**
 * Created by trifcdr.
 */

@Dao
interface DownloadDao {

    @Query("SELECT * FROM Download")
    fun getAll() : MutableList<Download>

    @Query("insert into Download(name, repoOwner) select * from(select :repoName, :repoOwner) as Download where not exists (select name from Download where name = :repoName) limit 1")
    fun insert(repoName: String, repoOwner: String)

    @Delete
    fun delete(download: Download)

}