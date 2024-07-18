package com.nandaiqbalh.pawartos.domain.usecase.news

import androidx.paging.PagingData
import com.nandaiqbalh.pawartos.domain.model.Article
import com.nandaiqbalh.pawartos.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNews(
	private val newsRepository: NewsRepository,
) {

	operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
		return newsRepository.searchNews(searchQuery, sources)
	}

}