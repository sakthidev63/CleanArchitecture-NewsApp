package com.sakthi.cleanarchitectureapp.feature_home.presendation.home

import com.sakthi.cleanarchitectureapp.feature_home.domain.model.News

data class HomeUIState(
    val isLoading: Boolean = true,
    val newsList: List<News> = emptyList(),
    val error: String? = null
)