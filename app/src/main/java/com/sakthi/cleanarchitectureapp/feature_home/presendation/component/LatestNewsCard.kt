package com.sakthi.cleanarchitectureapp.feature_home.presendation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sakthi.cleanarchitectureapp.MainScreen
import com.sakthi.cleanarchitectureapp.R
import com.sakthi.cleanarchitectureapp.core.Util.timeAgo
import com.sakthi.cleanarchitectureapp.feature_home.domain.model.News

@Composable
fun LatestNewsCard(news: News) {
    Row {
        Card(shape = RoundedCornerShape(8)) {
            AsyncImage(
                model = news.coverImage,
                contentDescription = news.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(105.dp)
                    .height(105.dp)
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(text = news.category.ifEmpty { "Default" }, fontSize = 14.sp, color = Color.DarkGray)
            Text(
                text = news.title,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Medium,
                maxLines = 2,
                fontSize = 16.sp,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bbc),
                    contentDescription = "",
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = news.source,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_clock),
                    contentDescription = "",
                    tint = Color.DarkGray
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = news.publishedAt.timeAgo(),
                    maxLines = 1,
                    fontSize = 15.sp,
                    color = Color.DarkGray
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen() {
    LatestNewsCard(
        News(
            title = "sahdajdh ashdasjkhdaj adghajhd ashj tgyhrtytytytytytty",
            coverImage = "",
            category = "Sports",
            source = "BBC news",
            publishedAt = "20h ago",
            isHeadlineNews = false
        )
    )
}