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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medinfo.ui.animation.NavigationAnimation
import com.example.medinfo.ui.composable.MedInfoScroll
import com.example.medinfo.ui.composable.MedInfoTextField
import com.example.medinfo.ui.composable.PostItem
import com.example.medinfo.ui.composable.PostListContent
import com.example.medinfo.ui.composable.homePost
import com.example.medinfo.ui.composable.postFooter
import com.example.medinfo.ui.composable.postImage
import com.example.medinfo.ui.screens.home.Post
import com.example.medinfo.ui.theme.textStyle11
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(style = NavigationAnimation::class)
@Composable
fun ArchiveScreen(navigator: DestinationsNavigator) {
    ArchiveContent()
}

@Composable
fun ArchiveContent() {
    MedInfoScroll {
        item { ArchiveSearch() }
        ArchivesList(
            archives = archiveList,
            postTitle = "არქივი",
            onSeeAllClick = { },
            onClick = { }
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
    onSeeAllClick: () -> Unit,
    onClick: () -> Unit
) {
    PostListContent(
        posts = archives,
        headerTitle = postTitle,
        fraction = .5f,
        columns = 2,
        isSeeAllButtonVisible = false,
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

val archiveList = listOf(
    Post("არქივი1"),
    Post("არქივი2"),
    Post("არქივი3"),
    Post("არქივი4"),
    Post("არქივი5"),
    Post("არქივი6"),
    Post("არქივი7"),
    Post("არქივი8"),
    Post("არქივი9"),
    Post("არქივი10"),
    Post("არქივი11"),
    Post("არქივი12")
)

@Preview
@Composable
fun NewsScreenPreview() {
    Surface(color = Color.White) {
        ArchiveContent()
    }
}