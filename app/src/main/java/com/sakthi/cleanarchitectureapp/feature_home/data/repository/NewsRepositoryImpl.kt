package com.sakthi.cleanarchitectureapp.feature_home.data.repository

import android.util.Log
import com.sakthi.cleanarchitectureapp.feature_home.data.local.NewsDAO
import com.sakthi.cleanarchitectureapp.feature_home.data.local.entity.toDomain
import com.sakthi.cleanarchitectureapp.feature_home.data.remote.NewsApiService
import com.sakthi.cleanarchitectureapp.feature_home.data.remote.dto.toEntity
import com.sakthi.cleanarchitectureapp.feature_home.domain.model.News
import com.sakthi.cleanarchitectureapp.feature_home.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsDao: NewsDAO,
    private val newsApiService: NewsApiService
) : NewsRepository {
    override suspend fun getAllTrendingNews(): Flow<Result<List<News>>> {
        return flow {
            try {

                val response = newsApiService.getTrendingNews()

                newsDao.insertNews(
                    response.articles
                        .map {
                            it.toEntity().copy(isHeadlineNews = true)
                        }
                )

                emit(Result.success(newsDao.getAllNews().map { it.toDomain() }))

            } catch (e: Exception) {
                val cachedNews = newsDao.getAllNews().map { it.toDomain() }

                if (cachedNews.isNotEmpty()) {
                    emit(Result.success(cachedNews))
                } else {
                    emit(Result.failure(e))
                }
            }

        }
    }

    override suspend fun getAllLatestNews(): Flow<Result<List<News>>> {
        return flow {
            try {

                val response = newsApiService.getNews()

                newsDao.insertNews(
                    response.articles
                        .map {
                            it.toEntity().copy(isHeadlineNews = false)
                        }
                )

                emit(Result.success(newsDao.getAllNews().map { it.toDomain() }))

            } catch (e: Exception) {
                val cachedNews = newsDao.getAllNews().map { it.toDomain() }

                if (cachedNews.isNotEmpty()) {
                    emit(Result.success(cachedNews))
                } else {
                    emit(Result.failure(e))
                }
            }

        }
    }

    override suspend fun getAllNewsByCategory(category: String): Flow<Result<List<News>>> {
        TODO("Not yet implemented")
    }
}