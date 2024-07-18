package com.nandaiqbalh.pawartos.domain.usecase.news

data class NewsUseCases(
	val getNews: GetNews,
	val searchNews: SearchNews,
	val upsertArticle: UpsertArticle,
	val selectArticles: SelectArticles,
	val deleteArticle: DeleteArticle
)
