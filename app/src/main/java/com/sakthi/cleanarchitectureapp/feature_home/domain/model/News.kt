package com.sakthi.cleanarchitectureapp.feature_home.domain.model

data class News(
    val title: String,
    val coverImage: String,
    val category: String,
    val source: String,
    val publishedAt: String,
    val isHeadlineNews: Boolean
)
