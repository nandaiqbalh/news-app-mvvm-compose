package com.nandaiqbalh.pawartos.domain.usecase.news

import com.nandaiqbalh.pawartos.domain.model.Article
import com.nandaiqbalh.pawartos.domain.repository.NewsRepository

class UpsertArticle(
	private val newsRepository: NewsRepository,
) {

	suspend operator fun invoke(article: Article) = newsRepository.upsertArticle(article)
}