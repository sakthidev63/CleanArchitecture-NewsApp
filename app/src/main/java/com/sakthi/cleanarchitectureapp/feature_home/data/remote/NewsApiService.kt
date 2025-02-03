package com.sakthi.cleanarchitectureapp.feature_home.data.remote

import com.sakthi.cleanarchitectureapp.feature_home.data.remote.dto.NewsData
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("/v2/top-headlines?country=us")
    suspend fun getTrendingNews(): NewsData

    @GET("/v2/everything?domains=bbc.co.uk")
    suspend fun getNews(): NewsData

    @GET("/v2/everything")
    suspend fun getNewsByCategory(@Query("category") category: String): NewsData

}