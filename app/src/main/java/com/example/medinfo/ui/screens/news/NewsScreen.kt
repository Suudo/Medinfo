package com.example.medinfo.ui.screens.news

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medinfo.ui.NavigationAnimation
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
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(),
        contentPadding = contentPadding
    ) {
        Search()
        Header()
        NewsList(
            newsList = newsList,
            onNewsClick = {}
        )
    }
}

fun LazyListScope.Search() {
    item {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = 8.dp)
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
                modifier = Modifier.height(34.dp).fillMaxWidth()
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

fun LazyListScope.Header() {
    item {
        PostHeader(
            postTitle = "სიახლე",
            isSeeAllButtonVisible = false,
            modifier = Modifier.padding(postHeaderPadding)
        )
    }
}

fun LazyListScope.NewsList(
    newsList: List<Post>,
    onNewsClick: () -> Unit
) {
    items(newsList) {
        PostItem(
            post = it,
            textStyle = textStyle14,
            imageModifier = Modifier.postImage(152.dp),
            footerModifier = Modifier.postFooter(48.dp),
            modifier = Modifier.homePost(200.dp),
            onPostClick = { onNewsClick() },
        )
    }
}

val contentPadding = PaddingValues(top = 16.dp, bottom = 70.dp, start = 8.dp, end = 8.dp)
val postHeaderPadding = PaddingValues(bottom = 8.dp, top = 16.dp, start = 8.dp, end = 8.dp)

@Preview
@Composable
fun NewsScreenPreview() {
    NewsContent()
}