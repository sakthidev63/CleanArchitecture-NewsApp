package com.sakthi.cleanarchitectureapp.feature_home.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class Source(
    val id: String = "",
    val name: String = ""
)