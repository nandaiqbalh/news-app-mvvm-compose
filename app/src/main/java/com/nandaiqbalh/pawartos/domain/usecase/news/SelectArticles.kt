package com.nandaiqbalh.pawartos.domain.usecase.news

import com.nandaiqbalh.pawartos.domain.model.Article
import com.nandaiqbalh.pawartos.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles(
	private val newsRepository: NewsRepository,
) {
	 operator fun invoke(): Flow<List<Article>> {
		 return newsRepository.selectArticles()
	}

}