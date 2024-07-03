package com.nandaiqbalh.pawartos.domain.usecase.app_entry

import com.nandaiqbalh.pawartos.domain.manager.LocalUserManager

class SaveAppEntry(
	private val localUserManager: LocalUserManager,
) {

	suspend operator fun invoke() {
		localUserManager.saveAppEntry()
	}
}