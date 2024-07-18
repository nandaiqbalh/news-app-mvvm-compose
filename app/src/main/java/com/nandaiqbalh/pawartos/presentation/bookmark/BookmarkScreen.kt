package com.nandaiqbalh.pawartos.presentation.bookmark

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.nandaiqbalh.pawartos.R
import com.nandaiqbalh.pawartos.presentation.Dimens.MediumPadding1
import com.nandaiqbalh.pawartos.presentation.common.ArticlesList
import com.nandaiqbalh.pawartos.presentation.navgraph.AppScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BookmarkScreen(
	state: BookmarkState,
	navigate: (String) -> Unit,
) {

	Column(
		modifier = Modifier
			.fillMaxSize()
			.statusBarsPadding()
			.padding(
				top = MediumPadding1,
				start = MediumPadding1,
				end = MediumPadding1
			)
	) {
		Text(
			text = "Bookmark",
			style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
			color = colorResource(id = R.color.text_title)
		)

		Spacer(modifier = Modifier.height(MediumPadding1))

		ArticlesList(
			articles = state.articles,
			onClick = { navigate(AppScreen.DetailScreen.route) })

	}

}