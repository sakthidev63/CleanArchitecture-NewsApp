package com.sakthi.cleanarchitectureapp.feature_home.data.remote.dto

import com.sakthi.cleanarchitectureapp.feature_home.data.local.entity.NewsEntity
import com.sakthi.cleanarchitectureapp.feature_home.domain.model.News
import kotlinx.serialization.Serializable

@Serializable
data class NewsData(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)



