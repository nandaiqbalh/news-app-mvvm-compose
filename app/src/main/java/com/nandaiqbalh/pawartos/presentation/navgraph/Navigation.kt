package com.nandaiqbalh.pawartos.presentation.navgraph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.nandaiqbalh.pawartos.presentation.news_navigator.NewsNavigator
import com.nandaiqbalh.pawartos.presentation.onboarding.OnBoardingScreen
import com.nandaiqbalh.pawartos.presentation.onboarding.OnBoardingViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(
	startDestination: String,
) {
	val navController = rememberNavController()

	NavHost(navController = navController, startDestination = startDestination) {

		// first sub nav
		navigation(
			route = AppScreen.AppStartNavigation.route,
			startDestination = AppScreen.OnBoardingScreen.route
		) {
			composable(
				route = AppScreen.OnBoardingScreen.route
			) {
				val viewModel: OnBoardingViewModel = hiltViewModel()
				OnBoardingScreen(event = {
					viewModel.onEvent(it)
				})
			}
		}

		// second sub nav
		navigation(
			route = AppScreen.NewsNavigation.route,
			startDestination = AppScreen.NewsNavigatorScreen.route
		) {
			composable(route = AppScreen.NewsNavigatorScreen.route) {
				NewsNavigator()
			}

		}
	}

}