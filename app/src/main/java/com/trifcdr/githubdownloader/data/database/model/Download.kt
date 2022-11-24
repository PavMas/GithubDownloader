package com.trifcdr.githubdownloader.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by trifcdr.
 */

@Entity
data class Download(@PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val repoOwner: String)