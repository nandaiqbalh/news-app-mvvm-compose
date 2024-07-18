package com.nandaiqbalh.pawartos.domain.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "article", primaryKeys = ["url"])
data class Article(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    val url: String,
    val urlToImage: String?
): Parcelable