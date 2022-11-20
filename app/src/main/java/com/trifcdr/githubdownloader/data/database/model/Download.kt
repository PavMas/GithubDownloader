package com.trifcdr.githubdownloader.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by trifcdr.
 */

@Entity
data class Download(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val repoOwner: String)