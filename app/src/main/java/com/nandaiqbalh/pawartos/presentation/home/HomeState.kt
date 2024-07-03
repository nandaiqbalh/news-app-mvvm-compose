package com.nandaiqbalh.pawartos.presentation.home


data class HomeState(
    val newsTicker: String = "",
    val isLoading: Boolean = false,
)