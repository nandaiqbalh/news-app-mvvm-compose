package com.nandaiqbalh.pawartos.domain.usecase.news

import com.nandaiqbalh.pawartos.domain.repository.NewsRepository

class GetArticleByUrl(
	private val newsRepository: NewsRepository,
) {
	suspend operator fun invoke(url: String) = newsRepository.selectArticleByURL(url)

}