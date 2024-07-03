package com.nandaiqbalh.pawartos.data.remote.dto

import com.nandaiqbalh.pawartos.domain.model.Article

data class NewsResponse(
	val articles: List<Article>,
	val status: String,
	val totalResults: Int
)