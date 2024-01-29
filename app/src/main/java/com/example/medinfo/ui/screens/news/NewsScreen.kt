package com.example.medinfo.ui.screens.news

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.medinfo.R
import com.example.medinfo.domain.model.Post
import com.example.medinfo.ui.animation.NavigationAnimation
import com.example.medinfo.ui.composable.MedInfoScroll
import com.example.medinfo.ui.composable.MedInfoTextField
import com.example.medinfo.ui.composable.PostHeader
import com.example.medinfo.ui.composable.PostItem
import com.example.medinfo.ui.composable.PostSize
import com.example.medinfo.ui.composable.postItemModifier
import com.example.medinfo.ui.composable.postFooterModifier
import com.example.medinfo.ui.composable.postImageModifier
import com.example.medinfo.ui.screens.destinations.DetailsBottomSheetDestination
import com.example.medinfo.ui.theme.textStyle14
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(style = NavigationAnimation::class)
@Composable
fun NewsScreen(navigator: DestinationsNavigator) {

    val viewModel: NewsViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    state.news?.let {
        NewsContent(
            state = state,
            navigateToDetail = { navigator.navigate(DetailsBottomSheetDestination) }
        )
    }
}

@Composable
fun NewsContent(
    state: NewsState,
    navigateToDetail: () -> Unit
) {
    MedInfoScroll {
        item { NewsSearch() }
        item { NewsHeader() }
        NewsItem(state.news!!) { navigateToDetail() }
    }
}

@Composable
fun NewsSearch() {
    var text by rememberSaveable { mutableStateOf("") }

    MedInfoTextField(
        search = text,
        onValueChange = { text = it }
    )
}

@Composable
fun NewsHeader() {
    val context = LocalContext.current

    PostHeader(
        postTitle = context.getString(R.string.title_news),
        isSeeAllButtonVisible = false,
        modifier = Modifier.padding(postHeaderPadding)
    )
}

fun LazyListScope.NewsItem(
    news: List<Post>,
    onNewsClick: () -> Unit
) {
    items(news) {
        PostItem(
            post = it,
            textStyle = textStyle14,
            imageModifier = Modifier.postImageModifier(PostSize.NEWS.imageHeight),
            footerModifier = Modifier.postFooterModifier(PostSize.NEWS.footerHeight),
            modifier = Modifier.postItemModifier(PostSize.NEWS.postHeight),
            onPostClick = { onNewsClick() },
        )
    }
}

val postHeaderPadding = PaddingValues(bottom = 8.dp, start = 8.dp, end = 8.dp)