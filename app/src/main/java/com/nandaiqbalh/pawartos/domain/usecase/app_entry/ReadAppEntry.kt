package com.nandaiqbalh.pawartos.domain.usecase.app_entry

import com.nandaiqbalh.pawartos.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
	private val localUserManager: LocalUserManager,
) {

	operator fun invoke(): Flow<Boolean> {
		return localUserManager.readAppEntry()
	}
}