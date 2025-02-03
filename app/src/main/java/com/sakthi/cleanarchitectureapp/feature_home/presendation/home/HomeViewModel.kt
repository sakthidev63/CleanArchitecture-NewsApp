package com.sakthi.cleanarchitectureapp.feature_home.presendation.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sakthi.cleanarchitectureapp.feature_home.domain.repository.NewsRepository
import com.sakthi.cleanarchitectureapp.feature_home.domain.use_case.GetCombinedNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCombinedNewsUseCase: GetCombinedNewsUseCase
): ViewModel() {

    var state by mutableStateOf(HomeUIState())

    init {
        getAllNews()
    }

    private fun getAllNews() {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            getCombinedNewsUseCase()
                .collect { result ->
                    state = if (result.isSuccess) {
                        state.copy(
                            isLoading = false,
                            newsList = result.getOrDefault(emptyList())
                        )
                    } else {
                        state.copy(
                            isLoading = false,
                            error = result.exceptionOrNull()?.message.toString()
                        )
                    }
                }
        }
    }

    fun onEvent(event: HomeEvent) {
        when(event) {
            HomeEvent.Latest -> TODO()
            HomeEvent.OnSearch -> TODO()
            HomeEvent.Trending -> TODO()
        }
    }

}