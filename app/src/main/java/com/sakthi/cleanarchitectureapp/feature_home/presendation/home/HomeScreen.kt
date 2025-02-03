package com.sakthi.cleanarchitectureapp.feature_home.presendation.home

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sakthi.cleanarchitectureapp.R
import com.sakthi.cleanarchitectureapp.feature_home.domain.model.News
import com.sakthi.cleanarchitectureapp.feature_home.presendation.component.CustomScrollableTabRow
import com.sakthi.cleanarchitectureapp.feature_home.presendation.component.LatestNewsCard
import com.sakthi.cleanarchitectureapp.feature_home.presendation.component.TitleText
import com.sakthi.cleanarchitectureapp.feature_home.presendation.component.TrendingCard
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state

    if (state.newsList.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier.padding(20.dp)
        ) {
            item { Gap(height = 20) }
            item { TitleView() }
            item { Gap(height = 10) }
            item { SearchBarView() }
            item { Gap(height = 20) }
            item { TitleText(title = "Trending") {} }
            item { Gap(height = 20) }
            item { TrendingCard(news = state.newsList[0]) }
            item { Gap(height = 20) }
            item { TitleText(title = "Latest") {} }
            item { Gap(height = 20) }
            item { CustomScrollableTabRow() }
            item { Gap(height = 20) }

            items(state.newsList.size) { index ->
                val isHeadlineNews = state.newsList[index].isHeadlineNews

                if (!isHeadlineNews) {
                    LatestNewsCard(news = state.newsList[index])
                    Gap(height = 20)
                }

            }
            item { Gap(height = 80) }
        }
   }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Center
    ) {
        if(state.isLoading) {
            CircularProgressIndicator()
        } else if(state.error != null && state.newsList.isEmpty()) {
            Text(
                text = state.error,
                color = Color.Red
            )
        }
    }
}

@Composable
fun TitleView() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "",
            Modifier.size(60.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Card(shape = RoundedCornerShape(6.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
            colors = CardDefaults.cardColors(
            containerColor = Color.White
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_notify),
                contentDescription = "",
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun SearchBarView() {
    Surface(
        border = BorderStroke(1.dp, Color.Black),
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(painter = painterResource(id = R.drawable.ic_search), contentDescription = "")
                Text(text = "Search", color = Color.Gray, textAlign = TextAlign.Center)
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_filter),
                contentDescription = "",
            )
        }
    }
}


@Composable
fun Gap(
    width: Int = 0,
    height: Int = 0
) {
    Spacer(modifier = if(width != 0) Modifier.width(width.dp) else Modifier.height(height.dp))
}





















