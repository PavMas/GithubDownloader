package com.trifcdr.githubdownloader.data.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by trifcdr.
 */

@Database(
    entities = [Database::class],
    version = 1
)
abstract class DownloadsDatabase : RoomDatabase() {

    abstract fun downloadDao() : DownloadDao

    companion object{
        var downloadDatabase: DownloadsDatabase? = null

        fun getInstance(context: Context) : DownloadsDatabase {
            if(downloadDatabase == null){
                downloadDatabase = Room.databaseBuilder(
                    context.applicationContext,
                    DownloadsDatabase::class.java,
                    "downloads_db")
                    .build()
            }
            return downloadDatabase!!
        }
    }
}