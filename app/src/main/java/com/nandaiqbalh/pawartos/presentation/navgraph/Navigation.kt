package com.nandaiqbalh.pawartos.presentation.navgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.nandaiqbalh.pawartos.presentation.onboarding.OnBoardingScreen
import com.nandaiqbalh.pawartos.presentation.onboarding.OnBoardingViewModel

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
			startDestination = AppScreen.HomeScreen.route
		) {
			composable(route = AppScreen.HomeScreen.route) {

			}
			composable(route = AppScreen.SearchScreen.route) {

			}
			composable(route = AppScreen.BookmarkScreen.route) {

			}
			composable(route = AppScreen.DetailScreen.route) {

			}
		}
	}

}