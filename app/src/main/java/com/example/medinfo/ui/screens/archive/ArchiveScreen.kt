package com.example.medinfo.ui.screens.archive

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.medinfo.R
import com.example.medinfo.domain.model.Post
import com.example.medinfo.ui.animation.NavigationAnimation
import com.example.medinfo.ui.composable.MedInfoScroll
import com.example.medinfo.ui.composable.MedInfoTextField
import com.example.medinfo.ui.composable.PostItem
import com.example.medinfo.ui.composable.PostListContent
import com.example.medinfo.ui.composable.PostSize
import com.example.medinfo.ui.composable.postItemModifier
import com.example.medinfo.ui.composable.postFooterModifier
import com.example.medinfo.ui.composable.postImageModifier
import com.example.medinfo.ui.theme.textStyle11
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(style = NavigationAnimation::class)
@Composable
fun ArchiveScreen(navigator: DestinationsNavigator) {

    val viewModel: ArchiveViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    state.archives?.let {
        ArchiveContent(
            state = state,
            navigateToDetail = { }
        )
    }
}

@Composable
fun ArchiveContent(
    state: ArchiveState,
    navigateToDetail: () -> Unit
) {
    val context = LocalContext.current

    MedInfoScroll {
        item { ArchiveSearch() }
        ArchivesList(
            archives = state.archives!!,
            postTitle = context.getString(R.string.title_archives),
            onClick = { navigateToDetail() }
        )
    }
}

@Composable
fun ArchiveSearch() {
    var text by rememberSaveable { mutableStateOf("") }

    MedInfoTextField(
        search = text,
        onValueChange = { text = it }
    )
}

fun LazyListScope.ArchivesList(
    archives: List<Post>,
    postTitle: String,
    onClick: () -> Unit
) {
    PostListContent(
        posts = archives,
        headerTitle = postTitle,
        isSeeAllButtonVisible = false,
        postSizes = PostSize.ARCHIVES,
    ) { item, modifier, imageHeight, footerHeight, postHeight ->
        PostItem(
            post = item,
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
fun NewsScreenPreview() {
    Surface(color = Color.White) {
        ArchiveContent(ArchiveState()){}
    }
}