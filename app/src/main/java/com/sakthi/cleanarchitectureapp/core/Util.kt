package com.sakthi.cleanarchitectureapp.core

import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object Util {

    fun String.timeAgo(): String {
        val formatter = DateTimeFormatter.ISO_DATE_TIME
        val givenTime = LocalDateTime.parse(this, formatter)
        val now = LocalDateTime.now(ZoneOffset.UTC)

        val duration = Duration.between(givenTime, now)

        return when {
            duration.toHours() < 1 -> "${duration.toMinutes()}m ago"
            duration.toDays() < 1 -> "${duration.toHours()}h ago"
            else -> "${duration.toDays()} days ago"
        }
    }

}