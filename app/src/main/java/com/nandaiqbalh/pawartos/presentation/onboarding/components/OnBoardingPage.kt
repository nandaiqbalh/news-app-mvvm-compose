package com.nandaiqbalh.pawartos.presentation.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.nandaiqbalh.pawartos.R
import com.nandaiqbalh.pawartos.presentation.Dimens.MediumPadding1
import com.nandaiqbalh.pawartos.presentation.Dimens.MediumPadding2
import com.nandaiqbalh.pawartos.presentation.onboarding.Page

@Composable
fun OnBoardingPage(
	page: Page,
	modifier: Modifier = Modifier,
) {

	Column(modifier = modifier) {
		Image(
			modifier = Modifier
				.fillMaxWidth()
				.fillMaxHeight(0.6f),
			painter = painterResource(id = page.image),
			contentDescription = "Image on boarding",
			contentScale = ContentScale.Crop
		)
		Spacer(modifier = Modifier.height(MediumPadding1))
		Text(
			modifier = Modifier.padding(horizontal = MediumPadding2),
			text = page.title,
			style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
			color = colorResource(id = R.color.display_small)
		)
		Text(
			modifier = Modifier.padding(horizontal = MediumPadding2),
			text = page.description,
			style = MaterialTheme.typography.bodyMedium,
			color = colorResource(id = R.color.text_medium)
		)
	}

}