package com.nandaiqbalh.pawartos.domain.repository

import androidx.paging.PagingData
import com.nandaiqbalh.pawartos.data.remote.SearchPagingSource
import com.nandaiqbalh.pawartos.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

	fun getNews(sources: List<String>): Flow<PagingData<Article>>

	fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>

}