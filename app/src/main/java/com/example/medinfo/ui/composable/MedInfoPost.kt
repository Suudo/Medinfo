package com.example.medinfo.ui.composable

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.medinfo.R
import com.example.medinfo.ui.screens.home.Post
import com.example.medinfo.ui.theme.textStyle12300
import com.example.medinfo.ui.theme.textStyle16

fun LazyListScope.PostListContent(
    posts: List<Post>,
    headerTitle: String,
    fraction: Float,
    columns: Int,
    isSeeAllButtonVisible: Boolean = true,
    onSeeAllClick: () -> Unit,
    content: @Composable (Post, Modifier) -> Unit
) {
    item {
        PostHeader(
            postTitle = headerTitle,
            isSeeAllButtonVisible = isSeeAllButtonVisible,
            onSeeAllClick = { onSeeAllClick() },
            modifier = Modifier.padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
        )
    }
    items(posts.windowed(columns, columns, true)) { items ->
        Row(modifier = Modifier.fillMaxWidth()) {
            items.forEach {
                content(it, Modifier.fillParentMaxWidth(fraction))
            }
        }
    }
}

@Composable
fun PostItem(
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    footerModifier: Modifier = Modifier,
    post: Post,
    textStyle: TextStyle,
    onPostClick: () -> Unit,
) {
    Box(modifier.clickable { onPostClick() }) {
        Image(
            painter = painterResource(id = R.drawable.ic_post),
            contentScale = ContentScale.FillBounds,
            contentDescription = "",
            modifier = imageModifier
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = footerModifier.align(Alignment.BottomCenter)
        ) {
            Text(
                text = post.name,
                style = textStyle,
                color = Color.White,
            )
            Image(
                painter = painterResource(id = R.drawable.ic_post_arrow),
                contentDescription = ""
            )
        }
    }
}

@Composable
fun PostHeader(
    modifier: Modifier = Modifier,
    postTitle: String,
    isSeeAllButtonVisible: Boolean = true,
    onSeeAllClick: () -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = postTitle,
            style = textStyle16,
            color = Color(0xFF2D2E2B),
        )
        AnimatedVisibility(visible = isSeeAllButtonVisible) {
            Column(Modifier.clickable { onSeeAllClick() }) {
                Text(
                    text = "ყველა",
                    color = Color(0xFF64665E),
                    style = textStyle12300
                )
                Spacer(
                    modifier = Modifier
                        .padding(top = 2.dp)
                        .height(0.5.dp)
                        .width(41.dp)
                        .background(Color(0xFF64665E))
                )
            }
        }
    }
}

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.homePost(height: Dp) =
    padding(bottom = 16.dp)
        .height(height)
        .background(
            color = Color.Transparent,
            shape = RoundedCornerShape(6.dp)
        )
        .padding(horizontal = 8.dp)


@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.postImage(height: Dp) =
    shadow(10.dp)
        .height(height)
        .fillMaxWidth()
        .background(
            color = Color.Transparent,
            shape = RoundedCornerShape(6.dp)
        )

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.postFooter(height: Dp) =
    shadow(
        elevation = 4.dp,
        spotColor = Color(0x4D000000),
        ambientColor = Color(0x4D000000)
    )
        .fillMaxWidth()
        .height(height)
        .background(
            color = Color(0xFF028FF6),
            shape = RoundedCornerShape(bottomStart = 6.dp, bottomEnd = 6.dp)
        )
        .padding(8.dp)