package com.example.medinfo.ui.screens.archive

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medinfo.ui.NavigationAnimation
import com.example.medinfo.ui.composable.PostHeader
import com.example.medinfo.ui.composable.PostItem
import com.example.medinfo.ui.composable.PostListContent
import com.example.medinfo.ui.composable.homePost
import com.example.medinfo.ui.composable.postFooter
import com.example.medinfo.ui.composable.postImage
import com.example.medinfo.ui.screens.home.HomeContent
import com.example.medinfo.ui.screens.home.Post
import com.example.medinfo.ui.screens.home.archivesList
import com.example.medinfo.ui.screens.home.newsList
import com.example.medinfo.ui.theme.textStyle11
import com.example.medinfo.ui.theme.textStyle14
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(style = NavigationAnimation::class)
@Composable
fun ArchiveScreen(navigator: DestinationsNavigator) {

    ArchiveContent()

}


@Composable
fun ArchiveContent() {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = contentPadding
    ) {
        ArchiveSearch()
        ArchivesList(
            archives = archiveList,
            postTitle = "არქივი",
            onSeeAllClick = { },
            onClick = {  }
        )
    }
}

fun LazyListScope.ArchiveSearch() {
    item {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, bottom = 16.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = Color.White)
                .border(
                    width = 1.dp,
                    color = Color(0xFF028FF6),
                    shape = RoundedCornerShape(6.dp),
                )
                .padding(horizontal = 8.dp)
        ) {
            var value by remember { mutableStateOf("") }
            TextField(
                value = value,
                onValueChange = { value = it },
                singleLine = true,
                textStyle = textStyle14,
                placeholder = { },
                colors = getTextfieldColors(),
                modifier = Modifier
                    .height(34.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun getTextfieldColors(): TextFieldColors {
    return TextFieldDefaults.textFieldColors(
        cursorColor = Color.Blue,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        backgroundColor = Color.Transparent
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

val contentPadding = PaddingValues(top = 16.dp, bottom = 70.dp, start = 8.dp, end = 8.dp)

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
    ArchiveContent()
}