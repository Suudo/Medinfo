package com.example.medinfo.ui.screens.news

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medinfo.ui.animation.NavigationAnimation
import com.example.medinfo.ui.composable.MedInfoScroll
import com.example.medinfo.ui.composable.MedInfoTextField
import com.example.medinfo.ui.composable.PostHeader
import com.example.medinfo.ui.composable.PostItem
import com.example.medinfo.ui.composable.homePost
import com.example.medinfo.ui.composable.postFooter
import com.example.medinfo.ui.composable.postImage
import com.example.medinfo.ui.screens.home.Post
import com.example.medinfo.ui.screens.home.newsList
import com.example.medinfo.ui.theme.textStyle14
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(style = NavigationAnimation::class)
@Composable
fun NewsScreen(navigator: DestinationsNavigator) {
    NewsContent()
}

@Composable
fun NewsContent() {
    MedInfoScroll {
        item { NewsSearch() }
        item { NewsHeader() }
        items(newsList) { NewsListItem(it) {} }
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
    PostHeader(
        postTitle = "სიახლე",
        isSeeAllButtonVisible = false,
        modifier = Modifier.padding(postHeaderPadding)
    )
}

@Composable
fun NewsListItem(
    post: Post,
    onNewsClick: () -> Unit
) {
    PostItem(
        post = post,
        textStyle = textStyle14,
        imageModifier = Modifier.postImage(152.dp),
        footerModifier = Modifier.postFooter(48.dp),
        modifier = Modifier.homePost(200.dp),
        onPostClick = { onNewsClick() },
    )
}

val postHeaderPadding = PaddingValues(bottom = 8.dp, start = 8.dp, end = 8.dp)

@Preview
@Composable
fun NewsScreenPreview() {
    Surface(color = Color.White) {
        NewsContent()
    }
}