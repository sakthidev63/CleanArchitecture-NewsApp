package com.sakthi.cleanarchitectureapp.feature_home.domain.repository

import com.sakthi.cleanarchitectureapp.feature_home.domain.model.News
import kotlinx.coroutines.flow.Flow
import kotlin.Result

interface NewsRepository {

    suspend fun getAllTrendingNews(): Flow<Result<List<News>>>

    suspend fun getAllLatestNews(): Flow<Result<List<News>>>

    suspend fun getAllNewsByCategory(category: String): Flow<Result<List<News>>>

}