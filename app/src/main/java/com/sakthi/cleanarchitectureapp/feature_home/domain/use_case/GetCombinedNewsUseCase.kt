package com.sakthi.cleanarchitectureapp.feature_home.domain.use_case

import android.util.Log
import com.sakthi.cleanarchitectureapp.feature_home.domain.model.News
import com.sakthi.cleanarchitectureapp.feature_home.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCombinedNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(): Flow<Result<List<News>>> = flow {
        try {
            combine(
                newsRepository.getAllTrendingNews(),
                newsRepository.getAllLatestNews()
            ) { trendingResult, latestResult ->

                when {
                    trendingResult.isSuccess && latestResult.isSuccess -> {
                        val combinedList = trendingResult.getOrNull().orEmpty() +
                                latestResult.getOrNull().orEmpty()
                        Result.success(combinedList)
                    }

                    else -> Result.failure(Exception("Unknown Error"))
                }

            }.collect {
                emit(it)
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}