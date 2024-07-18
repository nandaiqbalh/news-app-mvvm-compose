package com.nandaiqbalh.pawartos.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nandaiqbalh.pawartos.data.local.NewsDao
import com.nandaiqbalh.pawartos.data.remote.NewsApi
import com.nandaiqbalh.pawartos.data.remote.NewsPagingSource
import com.nandaiqbalh.pawartos.data.remote.SearchPagingSource
import com.nandaiqbalh.pawartos.domain.model.Article
import com.nandaiqbalh.pawartos.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class NewsRepositoryImpl(
	private val newsApi: NewsApi,
	private val newsDao: NewsDao
) : NewsRepository {

	override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
		return Pager(
			config = PagingConfig(pageSize = 10),
			pagingSourceFactory = {
				NewsPagingSource(
					newsApi = newsApi,
					sources = sources.joinToString(separator = ",")
				)
			}
		).flow
	}

	override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
		return Pager(
			config = PagingConfig(pageSize = 10),
			pagingSourceFactory = {
				SearchPagingSource(
					newsApi = newsApi,
					searchQuery = searchQuery,
					sources = sources.joinToString(separator = ",")
				)
			}
		).flow
	}

	override suspend fun upsertArticle(article: Article) {
		newsDao.upsert(article)
	}

	override suspend fun deleteArticle(article: Article) {
		newsDao.deleteArticle(article)
	}

	override fun selectArticles(): Flow<List<Article>> {
		return newsDao.getAllArticles().onEach { it.reversed() }
	}

	override suspend fun selectArticleByURL(url: String): Article? {
		return newsDao.getArticleByUrl(url)
	}
}