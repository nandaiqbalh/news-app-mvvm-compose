package com.nandaiqbalh.pawartos.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nandaiqbalh.pawartos.data.remote.NewsApi
import com.nandaiqbalh.pawartos.data.remote.NewsPagingSource
import com.nandaiqbalh.pawartos.domain.model.Article
import com.nandaiqbalh.pawartos.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
	private val newsApi: NewsApi,
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
}