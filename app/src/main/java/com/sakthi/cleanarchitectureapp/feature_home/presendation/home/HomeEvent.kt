package com.sakthi.cleanarchitectureapp.feature_home.presendation.home

sealed interface HomeEvent {
    data object OnSearch : HomeEvent
    data object Trending : HomeEvent
    data object Latest : HomeEvent
}