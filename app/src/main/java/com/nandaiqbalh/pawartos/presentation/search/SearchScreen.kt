package com.nandaiqbalh.pawartos.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.nandaiqbalh.pawartos.presentation.Dimens.MediumPadding1
import com.nandaiqbalh.pawartos.presentation.common.ArticlesList
import com.nandaiqbalh.pawartos.presentation.common.SearchBar
import com.nandaiqbalh.pawartos.presentation.navgraph.AppScreen

@Composable
fun SearchScreen(
	searchState: SearchState,
	event: (SearchEvent) -> Unit,
	navigate: (String) -> Unit,
) {

	Column(
		modifier = Modifier
			.padding(
				top = MediumPadding1,
				start = MediumPadding1,
				end = MediumPadding1,
			)
			.statusBarsPadding()
			.fillMaxSize()
	) {
		SearchBar(
			text = searchState.searchQuery,
			readOnly = false,
			onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
			onSearch = { event(SearchEvent.SearchNews) })

		Spacer(modifier = Modifier.height(MediumPadding1))

		searchState.articles?.let {
			val articles = it.collectAsLazyPagingItems()
			ArticlesList(articles = articles, onClick = { navigate(AppScreen.DetailScreen.route) })
		}


	}

}