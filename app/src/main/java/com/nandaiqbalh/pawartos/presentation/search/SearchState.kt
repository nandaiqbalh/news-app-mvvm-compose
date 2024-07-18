package com.nandaiqbalh.pawartos.presentation.search

import androidx.paging.PagingData
import com.nandaiqbalh.pawartos.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
	val searchQuery: String = "",
	val articles: Flow<PagingData<Article>>? = null
)