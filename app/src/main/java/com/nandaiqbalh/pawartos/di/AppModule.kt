package com.nandaiqbalh.pawartos.di

import android.app.Application
import com.nandaiqbalh.pawartos.data.manager.LocalUserManagerImpl
import com.nandaiqbalh.pawartos.data.remote.NewsApi
import com.nandaiqbalh.pawartos.data.repository.NewsRepositoryImpl
import com.nandaiqbalh.pawartos.domain.manager.LocalUserManager
import com.nandaiqbalh.pawartos.domain.repository.NewsRepository
import com.nandaiqbalh.pawartos.domain.usecase.app_entry.AppEntryUseCases
import com.nandaiqbalh.pawartos.domain.usecase.app_entry.ReadAppEntry
import com.nandaiqbalh.pawartos.domain.usecase.app_entry.SaveAppEntry
import com.nandaiqbalh.pawartos.domain.usecase.news.GetNews
import com.nandaiqbalh.pawartos.domain.usecase.news.NewsUseCases
import com.nandaiqbalh.pawartos.domain.usecase.news.SearchNews
import com.nandaiqbalh.pawartos.util.Constant.BASE_URL
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
	): NewsRepository = NewsRepositoryImpl(newsApi)

	@Provides
	@Singleton
	fun provideNewsUseCases(newsRepository: NewsRepository): NewsUseCases {
		return NewsUseCases(
			getNews = GetNews(newsRepository),
			searchNews = SearchNews(newsRepository)
		)
	}


}