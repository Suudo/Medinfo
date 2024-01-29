package com.example.medinfo.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.medinfo.R
import com.example.medinfo.domain.model.Post
import com.example.medinfo.ui.animation.NavigationAnimation
import com.example.medinfo.ui.composable.MedInfoScroll
import com.example.medinfo.ui.composable.PostItem
import com.example.medinfo.ui.composable.PostListContent
import com.example.medinfo.ui.composable.PostSize
import com.example.medinfo.ui.composable.postItemModifier
import com.example.medinfo.ui.composable.postFooterModifier
import com.example.medinfo.ui.composable.postImageModifier
import com.example.medinfo.ui.screens.destinations.ArchiveScreenDestination
import com.example.medinfo.ui.screens.destinations.DetailsBottomSheetDestination
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
    val state by viewModel.state.collectAsStateWithLifecycle()

    if (state.news != null && state.archives != null)
        HomeContent(
            state = state,
            navigateToNews = { navigator.navigate(NewsScreenDestination) },
            navigateToArchive = { navigator.navigate(ArchiveScreenDestination) },
            navigateToDetail = { navigator.navigate(DetailsBottomSheetDestination) }
        )
}

@Composable
fun HomeContent(
    state: HomeState,
    navigateToNews: () -> Unit,
    navigateToArchive: () -> Unit,
    navigateToDetail: () -> Unit
) {
    val context = LocalContext.current

    MedInfoScroll {
        NewsList(
            news = state.news!!,
            postTitle = context.getString(R.string.title_news),
            onSeeAllClick = navigateToNews,
            onClick = navigateToDetail
        )
        ArchivesList(
            archives = state.archives!!,
            postTitle = context.getString(R.string.title_archives),
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
        postSizes = PostSize.NEWS,
        onSeeAllClick = { onSeeAllClick() }
    ) { post, modifier, imageHeight, footerHeight, postHeight ->
        PostItem(
            post = post,
            textStyle = textStyle14,
            onPostClick = { onClick() },
            imageModifier = Modifier.postImageModifier(imageHeight),
            footerModifier = Modifier.postFooterModifier(footerHeight),
            modifier = modifier.postItemModifier(postHeight)
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
        postSizes = PostSize.ARCHIVES,
        onSeeAllClick = { onSeeAllClick() }
    ) { post, modifier, imageHeight, footerHeight, postHeight ->
        PostItem(
            post = post,
            textStyle = textStyle11,
            onPostClick = { onClick() },
            imageModifier = Modifier.postImageModifier(imageHeight),
            footerModifier = Modifier.postFooterModifier(footerHeight),
            modifier = modifier.postItemModifier(postHeight)
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeContent(HomeState(), {}, {}, {})
}