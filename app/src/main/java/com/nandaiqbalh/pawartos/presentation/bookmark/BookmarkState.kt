package com.nandaiqbalh.pawartos.presentation.bookmark

import com.nandaiqbalh.pawartos.domain.model.Article

data class BookmarkState(
	val articles: List<Article> = emptyList(),
)