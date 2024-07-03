package com.nandaiqbalh.pawartos.domain.usecase.news

import androidx.paging.PagingData
import com.nandaiqbalh.pawartos.domain.model.Article
import com.nandaiqbalh.pawartos.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow


class GetNews(
	private val newsRepository: NewsRepository,
) {

	operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
		return newsRepository.getNews(sources)
	}
}