package com.nandaiqbalh.pawartos.presentation.search

sealed class SearchEvent {

	data class UpdateSearchQuery(val query: String) : SearchEvent()

	object SearchNews : SearchEvent()

}