package com.nandaiqbalh.pawartos.domain.usecase.news

import com.nandaiqbalh.pawartos.data.local.NewsDao
import com.nandaiqbalh.pawartos.domain.model.Article

class UpsertArticle(
	private val newsDao: NewsDao,
) {

	suspend operator fun invoke(article: Article) = newsDao.upsert(article)
}