package com.nandaiqbalh.pawartos.domain.model

import androidx.room.Entity

@Entity(tableName = "article", primaryKeys = ["url"])
data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)