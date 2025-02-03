package com.sakthi.cleanarchitectureapp.feature_home.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sakthi.cleanarchitectureapp.feature_home.data.remote.dto.Article
import com.sakthi.cleanarchitectureapp.feature_home.domain.model.News

@Entity
data class NewsEntity(
    @PrimaryKey val title: String,
    val coverImage: String,
    val category: String,
    val source: String,
    val publishedAt: String,
    val isHeadlineNews: Boolean = false
)

fun NewsEntity.toDomain(): News {
    return News(
        title = title,
        coverImage = coverImage,
        category = category,
        source = source,
        publishedAt = publishedAt,
        isHeadlineNews = isHeadlineNews
    )
}