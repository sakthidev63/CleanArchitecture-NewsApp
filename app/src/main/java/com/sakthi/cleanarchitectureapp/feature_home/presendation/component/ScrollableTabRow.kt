package com.sakthi.cleanarchitectureapp.feature_home.presendation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sakthi.cleanarchitectureapp.feature_home.presendation.home.Gap

@Composable
fun CustomScrollableTabRow(
    tabs: List<String> = newsCategoryList,
) {
    LazyRow {
        items(tabs.size) {
            Column {
                Text(
                    text = tabs[it],
                    color = if (it == 0) MaterialTheme.colorScheme.primary else Color.DarkGray,
                    fontWeight = if (it == 0) FontWeight.Bold else FontWeight.Normal
                )
                Gap(height = 7)
                if (it == 0)
                Box(
                    modifier = Modifier
                        .widthIn(min = 20.dp)
                        .height(2.dp)
                        .background(
                            MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(20))
                ) {
                    Spacer(modifier = Modifier.fillMaxSize())
                }
            }
            Gap(width = 10)
        }
    }
}


val newsCategoryList = listOf(
    "All", "Sports", "Politics", "Business", "health", "Travel", "Science", "Food", "Games"
)

@Preview
@Composable
fun PreviewTabBar() {
    CustomScrollableTabRow(newsCategoryList)
}