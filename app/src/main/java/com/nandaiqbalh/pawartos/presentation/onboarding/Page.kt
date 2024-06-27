package com.nandaiqbalh.pawartos.presentation.onboarding

import androidx.annotation.DrawableRes
import com.nandaiqbalh.pawartos.R

data class Page(
	val title: String,
	val description: String,
	@DrawableRes val image: Int,
)

val pages = listOf(
	Page(
		title = "Stay Informed",
		description = "Get the latest headlines at your fingertips.",
		image = R.drawable.ob1
	),
	Page(
		title = "Discover Easily",
		description = "Find news that matters to you with a quick search.",
		image = R.drawable.ob2
	),
	Page(
		title = "Bookmark Favorites",
		description = "Save articles to read later and never miss out.",
		image = R.drawable.ob3
	),
)