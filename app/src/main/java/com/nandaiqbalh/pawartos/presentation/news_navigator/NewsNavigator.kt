package com.nandaiqbalh.pawartos.presentation.news_navigator

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.nandaiqbalh.pawartos.R
import com.nandaiqbalh.pawartos.domain.model.Article
import com.nandaiqbalh.pawartos.presentation.bookmark.BookmarkScreen
import com.nandaiqbalh.pawartos.presentation.bookmark.BookmarkViewModel
import com.nandaiqbalh.pawartos.presentation.details.DetailsEvent
import com.nandaiqbalh.pawartos.presentation.details.DetailsScreen
import com.nandaiqbalh.pawartos.presentation.details.DetailsViewModel
import com.nandaiqbalh.pawartos.presentation.home.HomeScreen
import com.nandaiqbalh.pawartos.presentation.home.HomeViewModel
import com.nandaiqbalh.pawartos.presentation.navgraph.AppScreen
import com.nandaiqbalh.pawartos.presentation.news_navigator.components.BottomNavigationItem
import com.nandaiqbalh.pawartos.presentation.news_navigator.components.NewsBottomNavigation
import com.nandaiqbalh.pawartos.presentation.search.SearchScreen
import com.nandaiqbalh.pawartos.presentation.search.SearchViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsNavigator() {

	val bottomNavigationItems = listOf(
		BottomNavigationItem(icon = R.drawable.ic_home, "Home"),
		BottomNavigationItem(icon = R.drawable.ic_search, "Search"),
		BottomNavigationItem(icon = R.drawable.ic_bookmark, "Bookmark")
	)

	val navController = rememberNavController()
	val backstackState = navController.currentBackStackEntryAsState().value
	var selectedItem by rememberSaveable {
		mutableIntStateOf(0)
	}

	selectedItem = remember(key1 = backstackState) {
		when (backstackState?.destination?.route) {
			AppScreen.HomeScreen.route -> 0
			AppScreen.SearchScreen.route -> 1
			AppScreen.BookmarkScreen.route -> 2
			else -> 0
		}
	}

	val isBottomBarVisible = remember(key1 = backstackState) {
		when (backstackState?.destination?.route) {
			AppScreen.HomeScreen.route -> true
			AppScreen.SearchScreen.route -> true
			AppScreen.BookmarkScreen.route -> true
			else -> false
		}
	}

	Scaffold(
		modifier = Modifier.fillMaxSize(),

		bottomBar = {

			if (isBottomBarVisible) {
				NewsBottomNavigation(
					items = bottomNavigationItems,
					selected = selectedItem,
					onItemClick = { index ->
						when (index) {
							0 -> navigateToTab(navController, AppScreen.HomeScreen.route)
							1 -> navigateToTab(navController, AppScreen.SearchScreen.route)
							2 -> navigateToTab(navController, AppScreen.BookmarkScreen.route)
						}
					}
				)
			}

		}
	) {

		val bottomPadding = it.calculateBottomPadding()

		NavHost(
			navController = navController,
			startDestination = AppScreen.HomeScreen.route,
			modifier = Modifier.padding(bottom = bottomPadding)
		) {
			composable(AppScreen.HomeScreen.route) {
				val viemodel: HomeViewModel = hiltViewModel()
				val articles = viemodel.news.collectAsLazyPagingItems()
				HomeScreen(
					articles = articles,
					navigateToSearch = {
						navigateToTab(navController, AppScreen.SearchScreen.route)
					},
					navigateToDetails = { articleDetail ->
						navigateToDetails(navController, articleDetail)
					}
				)
			}

			composable(AppScreen.SearchScreen.route) {
				val viewmodel: SearchViewModel = hiltViewModel()
				val state = viewmodel.state.value

				SearchScreen(
					searchState = state,
					event = viewmodel::onEvent,
					navigateToDetails = { article -> navigateToDetails(navController, article) })
			}

			composable(AppScreen.DetailScreen.route) {

				val viewModel: DetailsViewModel = hiltViewModel()
				if (viewModel.sideEffect != null) {
					Toast.makeText(LocalContext.current, viewModel.sideEffect, Toast.LENGTH_SHORT)
						.show()
					viewModel.onEvent(DetailsEvent.RemoveSideEffect)
				}

				navController.previousBackStackEntry?.savedStateHandle?.get<Article>("article")
					?.let { article ->
						DetailsScreen(
							article = article,
							event = viewModel::onEvent,
							navigateUp = { navController.navigateUp() })
					}

			}

			composable(AppScreen.BookmarkScreen.route) {
				val viewModel: BookmarkViewModel = hiltViewModel()

				val state = viewModel.state.value

				BookmarkScreen(state = state, navigateToDetails = { article ->
					navigateToDetails(navController, article)
				})
			}

		}

	}
}

private fun navigateToTab(navController: NavController, route: String) {
	navController.navigate(route) {
		navController.graph.startDestinationRoute?.let { homeScreen ->
			popUpTo(homeScreen) {
				saveState = true
			}
			restoreState = true
			launchSingleTop = true
		}

	}

}

private fun navigateToDetails(navController: NavController, article: Article) {
	navController.currentBackStackEntry?.savedStateHandle?.set("article", article)

	navController.navigate(AppScreen.DetailScreen.route)
}