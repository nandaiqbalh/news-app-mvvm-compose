package com.nandaiqbalh.pawartos.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.paging.compose.collectAsLazyPagingItems
import com.nandaiqbalh.pawartos.presentation.home.HomeScreen
import com.nandaiqbalh.pawartos.presentation.home.HomeViewModel
import com.nandaiqbalh.pawartos.presentation.onboarding.OnBoardingScreen
import com.nandaiqbalh.pawartos.presentation.onboarding.OnBoardingViewModel
import com.nandaiqbalh.pawartos.presentation.search.SearchScreen
import com.nandaiqbalh.pawartos.presentation.search.SearchViewModel

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
			startDestination = AppScreen.SearchScreen.route
		) {
			composable(route = AppScreen.HomeScreen.route) {
				val viewModel: HomeViewModel = hiltViewModel()
				val articles = viewModel.news.collectAsLazyPagingItems()

				HomeScreen(articles = articles, navigate = {})
			}
			composable(route = AppScreen.SearchScreen.route) {
				val viewModel: SearchViewModel = hiltViewModel()

				SearchScreen(
					searchState = viewModel.state.value,
					event = viewModel::onEvent,
					navigate = {})
			}
			composable(route = AppScreen.BookmarkScreen.route) {

			}
			composable(route = AppScreen.DetailScreen.route) {

			}
		}
	}

}