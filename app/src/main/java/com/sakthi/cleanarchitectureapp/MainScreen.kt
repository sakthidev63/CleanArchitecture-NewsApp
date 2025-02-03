package com.sakthi.cleanarchitectureapp

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sakthi.cleanarchitectureapp.feature_home.presendation.home.HomeScreen

@Composable
fun MainScreen() {
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(selectedIndex) { newIndex -> selectedIndex = newIndex } }
    ) { innerPadding ->
        ContentScreen(modifier = Modifier.padding(innerPadding), selectedIndex)
    }
}

@Composable
fun ContentScreen(modifier: Modifier, selectedIndex: Int) {
    when(selectedIndex) {
        0 -> HomeScreen()
    }
}

@Composable
fun BottomNavigationBar(selectedIndex: Int, onSelectItem: (Int) -> Unit) {

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Explore,
        BottomNavItem.Bookmark,
        BottomNavItem.Profile
    )

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 5.dp
    ) {
        items.forEachIndexed { index, bottomNavItem ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = { onSelectItem(index) },
                label = {
                    Text(
                        text = bottomNavItem.title,
                        color = if (selectedIndex == index) MaterialTheme.colorScheme.primary else Color.DarkGray
                    )

                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                ),
                icon = {
                    Icon(
                        painter = painterResource(id = if (selectedIndex == index) bottomNavItem.selectedIcon else bottomNavItem.icon),
                        contentDescription = bottomNavItem.title,
                        tint = if (selectedIndex == index) MaterialTheme.colorScheme.primary else Color.DarkGray
                    )
                }
            )
        }
    }
}

sealed class BottomNavItem(val title: String, @DrawableRes val icon: Int, @DrawableRes val selectedIcon: Int) {
    data object Home : BottomNavItem("Home", R.drawable.ic_home, R.drawable.ic_home_selected)
    data object Explore : BottomNavItem( "Explore", R.drawable.ic_explore, R.drawable.ic_explore_selected)
    data object Bookmark : BottomNavItem( "Bookmark", R.drawable.ic_bookmark, R.drawable.ic_bookmark_selected)
    data object Profile : BottomNavItem( "Profile", R.drawable.ic_profile, R.drawable.ic_profile_selected)
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen()
}