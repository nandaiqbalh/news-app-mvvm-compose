package com.nandaiqbalh.pawartos.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nandaiqbalh.pawartos.domain.usecase.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
	private val appEntryUseCases: AppEntryUseCases,
) : ViewModel() {

	fun onEvent(event: OnBoardingEvent){
		when (event){
			is OnBoardingEvent.SaveAppEntry -> {
				saveAppEntry()
			}
			else -> {}
		}
	}

	private fun saveAppEntry(){
		viewModelScope.launch {
			appEntryUseCases.saveAppEntry()
		}
	}
}