package com.sakthi.cleanarchitectureapp.feature_home.data.remote.dto

import com.sakthi.cleanarchitectureapp.feature_home.data.local.entity.NewsEntity
import com.sakthi.cleanarchitectureapp.feature_home.domain.model.News
import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val author: String = "",
    val content: String = "",
    val description: String = "",
    val publishedAt: String = "",
    val source: Source,
    val title: String = "",
    val url: String = "",
    val urlToImage: String = ""
)

fun Article.toEntity(): NewsEntity {
    return NewsEntity(
        title = title,
        coverImage = urlToImage,
        category = author,
        source = source.name,
        publishedAt = publishedAt,
    )
}