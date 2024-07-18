package com.nandaiqbalh.pawartos.domain.usecase.news

import com.nandaiqbalh.pawartos.data.local.NewsDao
import com.nandaiqbalh.pawartos.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SelectArticles(
	private val newsDao: NewsDao,
) {
	 operator fun invoke(): Flow<List<Article>> {
		 return newsDao.getAllArticles()
	}

}