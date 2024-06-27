package com.nandaiqbalh.pawartos

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nandaiqbalh.pawartos.domain.usecase.AppEntryUseCases
import com.nandaiqbalh.pawartos.presentation.navgraph.AppScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
	private val appEntryUseCases: AppEntryUseCases,
) : ViewModel() {

	private val _splashCondition = mutableStateOf(true)
	val splashCondition: State<Boolean> = _splashCondition

	private val _startDestination = mutableStateOf(AppScreen.AppStartNavigation.route)
	val startDestination: State<String> = _startDestination

	init {
		appEntryUseCases.readAppEntry().onEach { shouldStartFromHomeScreen ->
			if(shouldStartFromHomeScreen){
				_startDestination.value = AppScreen.NewsNavigation.route
			}else{
				_startDestination.value = AppScreen.AppStartNavigation.route
			}
			delay(200) //Without this delay, the onBoarding screen will show for a momentum.
			_splashCondition.value = false
		}.launchIn(viewModelScope)
	}
}