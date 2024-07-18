package com.nandaiqbalh.pawartos.presentation.common

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.nandaiqbalh.pawartos.domain.model.Article
import com.nandaiqbalh.pawartos.presentation.Dimens.ExtraSmallPadding2
import com.nandaiqbalh.pawartos.presentation.Dimens.MediumPadding1
import com.nandaiqbalh.pawartos.presentation.home.components.ArticleCard

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ArticlesList(
	modifier: Modifier = Modifier,
	articles: LazyPagingItems<Article>,
	onClick: (Article) -> Unit,
) {
	val handlePagingResult = HandlePagingResult(articles)

	if (handlePagingResult) {
		LazyColumn(
			modifier = modifier.fillMaxSize(),
			verticalArrangement = Arrangement.spacedBy(MediumPadding1),
			contentPadding = PaddingValues(all = ExtraSmallPadding2)
		) {
			items(
				count = articles.itemCount,
			) {
				articles[it]?.let { article ->
					ArticleCard(article = article, onClick = { onClick(article) })
				}
			}
		}
	}

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ArticlesList(
	modifier: Modifier = Modifier,
	articles: List<Article>,
	onClick: (Article) -> Unit,
) {


	LazyColumn(
		modifier = modifier.fillMaxSize(),
		verticalArrangement = Arrangement.spacedBy(MediumPadding1),
		contentPadding = PaddingValues(all = ExtraSmallPadding2)
	) {
		items(
			count = articles.size,
		) {
			val article = articles[it]
			ArticleCard(article = article, onClick = { onClick(article) })

		}

	}

}

@Composable
fun HandlePagingResult(
	articles: LazyPagingItems<Article>,
): Boolean {

	val loadState = articles.loadState

	val error = when {
		loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
		loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
		loadState.append is LoadState.Error -> loadState.append as LoadState.Error
		else -> null
	}

	return when {
		loadState.refresh is LoadState.Loading -> {
			ShimmerEffect()
			false
		}

		error != null -> {
			EmptyScreen()
			false
		}

		else -> {
			true
		}
	}
}

@Composable
private fun ShimmerEffect() {

	Column(verticalArrangement = Arrangement.spacedBy(MediumPadding1)) {

		repeat(10) {
			ArticleCardShimmerEffect(
				modifier = Modifier.padding(horizontal = MediumPadding1)
			)
		}

	}
}