package com.nandaiqbalh.pawartos.presentation.navgraph

sealed class AppScreen(
	val route: String,
) {

	data object OnBoardingScreen : AppScreen(route = "onBoardingScreen")
	data object HomeScreen : AppScreen(route = "homeScreen")
	data object SearchScreen : AppScreen(route = "searchScreen")
	data object BookmarkScreen : AppScreen(route = "bookmarkScreen")
	data object DetailScreen : AppScreen(route = "detailScreen")
	data object AppStartNavigation : AppScreen(route = "appStartNavigation")
	data object NewsNavigation : AppScreen(route = "newsNavigation")
	data object NewsNavigatorScreen : AppScreen(route = "newsNavigatorScreen")
}