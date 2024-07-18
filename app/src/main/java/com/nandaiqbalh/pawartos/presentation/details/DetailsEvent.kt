package com.nandaiqbalh.pawartos.presentation.details

import com.nandaiqbalh.pawartos.domain.model.Article

sealed class DetailsEvent {

	data class UpsertDeleteArticle(val article: Article) : DetailsEvent()

	data object RemoveSideEffect: DetailsEvent()
}
