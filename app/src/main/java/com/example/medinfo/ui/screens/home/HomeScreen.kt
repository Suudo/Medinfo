@file:OptIn(ExperimentalFoundationApi::class)

package com.example.medinfo.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.medinfo.ui.NavigationAnimation
import com.example.medinfo.ui.composable.PostItem
import com.example.medinfo.ui.composable.PostListContent
import com.example.medinfo.ui.composable.homePost
import com.example.medinfo.ui.composable.postFooter
import com.example.medinfo.ui.composable.postImage
import com.example.medinfo.ui.screens.destinations.ArchiveScreenDestination
import com.example.medinfo.ui.screens.destinations.NewsScreenDestination
import com.example.medinfo.ui.theme.textStyle11
import com.example.medinfo.ui.theme.textStyle14
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination(
    start = true,
    style = NavigationAnimation::class
)
@Composable
fun HomeScreen(navigator: DestinationsNavigator) {
    val viewModel: HomeViewModel = hiltViewModel()

    HomeContent(
        navigateToNews = { navigator.navigate(NewsScreenDestination, onlyIfResumed = false) },
        navigateToArchive = { navigator.navigate(ArchiveScreenDestination, onlyIfResumed = true) },
        navigateToDetail = { }
    )
}

@Composable
fun HomeContent(
    navigateToNews: () -> Unit,
    navigateToArchive: () -> Unit,
    navigateToDetail: () -> Unit
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = homePadding
    ) {
        NewsList(
            news = newsList,
            postTitle = "სიახლე",
            onSeeAllClick = navigateToNews,
            onClick = navigateToDetail
        )
        ArchivesList(
            archives = archivesList,
            postTitle = "არქივი",
            onSeeAllClick = navigateToArchive,
            onClick = navigateToDetail
        )
    }
}

fun LazyListScope.NewsList(
    news: List<Post>,
    postTitle: String,
    onSeeAllClick: () -> Unit,
    onClick: () -> Unit
) {
    PostListContent(
        posts = news,
        headerTitle = postTitle,
        fraction = 1f,
        columns = 1,
        onSeeAllClick = { onSeeAllClick() }
    ) { item, modifier ->
        PostItem(
            post = item,
            textStyle = textStyle14,
            onPostClick = { onClick() },
            imageModifier = Modifier.postImage(152.dp),
            footerModifier = Modifier.postFooter(48.dp),
            modifier = modifier.homePost(200.dp)
        )
    }
}

fun LazyListScope.ArchivesList(
    archives: List<Post>,
    postTitle: String,
    onSeeAllClick: () -> Unit,
    onClick: () -> Unit
) {
    PostListContent(
        posts = archives,
        headerTitle = postTitle,
        fraction = .5f,
        columns = 2,
        onSeeAllClick = { onSeeAllClick() }
    ) { item, modifier ->
        PostItem(
            post = item,
            textStyle = textStyle11,
            onPostClick = { onClick() },
            imageModifier = Modifier.postImage(76.dp),
            footerModifier = Modifier.postFooter(32.dp),
            modifier = modifier.homePost(100.dp)
        )
    }
}

data class Post(val name: String)

val newsList = listOf(
    Post("სათაური1"),
    Post("სათაური2"),
    Post("სათაური3"),
    Post("სათაური4")
)

val archivesList = listOf(
    Post("სათაური5"),
    Post("სათაური6"),
    Post("სათაური7"),
    Post("სათაური8")
)

val homePadding = PaddingValues(start = 8.dp, end = 8.dp, bottom = 70.dp, top = 16.dp)

@Preview
@Composable
fun HomeScreenPreview() {
    HomeContent({}, {}, {})
}