package com.nandaiqbalh.pawartos.di

import android.app.Application
import androidx.room.Room
import com.nandaiqbalh.pawartos.data.local.NewsDao
import com.nandaiqbalh.pawartos.data.local.NewsDatabase
import com.nandaiqbalh.pawartos.data.local.NewsTypeConverter
import com.nandaiqbalh.pawartos.data.manager.LocalUserManagerImpl
import com.nandaiqbalh.pawartos.data.remote.NewsApi
import com.nandaiqbalh.pawartos.data.repository.NewsRepositoryImpl
import com.nandaiqbalh.pawartos.domain.manager.LocalUserManager
import com.nandaiqbalh.pawartos.domain.repository.NewsRepository
import com.nandaiqbalh.pawartos.domain.usecase.app_entry.AppEntryUseCases
import com.nandaiqbalh.pawartos.domain.usecase.app_entry.ReadAppEntry
import com.nandaiqbalh.pawartos.domain.usecase.app_entry.SaveAppEntry
import com.nandaiqbalh.pawartos.domain.usecase.news.DeleteArticle
import com.nandaiqbalh.pawartos.domain.usecase.news.GetArticleByUrl
import com.nandaiqbalh.pawartos.domain.usecase.news.GetNews
import com.nandaiqbalh.pawartos.domain.usecase.news.NewsUseCases
import com.nandaiqbalh.pawartos.domain.usecase.news.SearchNews
import com.nandaiqbalh.pawartos.domain.usecase.news.SelectArticles
import com.nandaiqbalh.pawartos.domain.usecase.news.UpsertArticle
import com.nandaiqbalh.pawartos.util.Constant.BASE_URL
import com.nandaiqbalh.pawartos.util.Constant.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

	@Provides
	@Singleton
	fun provideLocalUserManager(
		application: Application,
	): LocalUserManager {
		return LocalUserManagerImpl(application)
	}

	@Provides
	@Singleton
	fun provideAppEntryUseCases(
		localUserManager: LocalUserManager,
	): AppEntryUseCases {
		return AppEntryUseCases(
			readAppEntry = ReadAppEntry(localUserManager),
			saveAppEntry = SaveAppEntry(localUserManager)
		)
	}


	@Provides
	@Singleton
	fun provideNewsApi(): NewsApi {
		return Retrofit.Builder().baseUrl(BASE_URL)
			.addConverterFactory(GsonConverterFactory.create()).build().create(NewsApi::class.java)
	}


	@Provides
	@Singleton
	fun provideNewsRepository(
		newsApi: NewsApi,
		newsDao: NewsDao,
	): NewsRepository = NewsRepositoryImpl(newsApi, newsDao)

	@Provides
	@Singleton
	fun provideNewsUseCases(
		newsRepository: NewsRepository,
	): NewsUseCases {
		return NewsUseCases(
			getNews = GetNews(newsRepository),
			searchNews = SearchNews(newsRepository),
			upsertArticle = UpsertArticle(newsRepository),
			selectArticles = SelectArticles(newsRepository),
			deleteArticle = DeleteArticle(newsRepository),
			getArticleByUrl = GetArticleByUrl(newsRepository)
		)
	}

	@Provides
	@Singleton
	fun provideNewsDatabase(app: Application): NewsDatabase {
		return Room.databaseBuilder(app, NewsDatabase::class.java, NEWS_DATABASE_NAME)
			.addTypeConverter(
				NewsTypeConverter()
			).fallbackToDestructiveMigration().build()
	}

	@Provides
	@Singleton
	fun provideNewsDao(db: NewsDatabase) = db.newsDao


}