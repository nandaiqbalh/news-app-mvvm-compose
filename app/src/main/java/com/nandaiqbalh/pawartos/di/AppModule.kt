package com.nandaiqbalh.pawartos.di

import android.app.Application
import com.nandaiqbalh.pawartos.data.manager.LocalUserManagerImpl
import com.nandaiqbalh.pawartos.domain.manager.LocalUserManager
import com.nandaiqbalh.pawartos.domain.usecase.AppEntryUseCases
import com.nandaiqbalh.pawartos.domain.usecase.ReadAppEntry
import com.nandaiqbalh.pawartos.domain.usecase.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
}