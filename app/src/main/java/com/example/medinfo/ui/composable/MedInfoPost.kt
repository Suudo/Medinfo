package com.example.medinfo.ui.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import com.example.medinfo.domain.model.Post
import com.example.medinfo.ui.theme.textStyle12300
import com.example.medinfo.ui.theme.textStyle16

fun LazyListScope.PostListContent(
    posts: List<Post>,
    headerTitle: String,
    postSizes: PostSize,
    isSeeAllButtonVisible: Boolean = true,
    onSeeAllClick: () -> Unit = {},
    content: @Composable (Post, Modifier, Dp, Dp, Dp) -> Unit
) {
    item {
        PostHeader(
            postTitle = headerTitle,
            isSeeAllButtonVisible = isSeeAllButtonVisible,
            onSeeAllClick = { onSeeAllClick() },
            modifier = Modifier.padding(postHeaderPadding)
        )
    }
    items(posts.windowed(postSizes.columns, postSizes.columns, true)) {
        Row(Modifier.fillMaxWidth()) {
            it.forEach { post ->
                content(
                    post,
                    Modifier.fillParentMaxWidth(postSizes.fraction),
                    postSizes.imageHeight,
                    postSizes.footerHeight,
                    postSizes.postHeight
                )
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
            painter = painterResource(post.image),
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
                text = post.title,
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
        if (isSeeAllButtonVisible) SeeAllContent { onSeeAllClick() }
    }
}

@Composable
fun SeeAllContent(onSeeAllClick: () -> Unit) {
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

enum class PostSize(
    val fraction: Float,
    val columns: Int,
    val imageHeight: Dp,
    val footerHeight: Dp,
    val postHeight: Dp
) {
    NEWS(1f, 1, 152.dp, 48.dp, 200.dp),
    ARCHIVES(.5f, 2, 76.dp, 32.dp, 100.dp)
}

val postHeaderPadding = PaddingValues(bottom = 8.dp, start = 8.dp, end = 8.dp)

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.postItemModifier(height: Dp) =
    padding(bottom = 16.dp)
        .height(height)
        .background(
            color = Color.Transparent,
            shape = RoundedCornerShape(6.dp)
        )
        .padding(horizontal = 8.dp)


@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.postImageModifier(height: Dp) =
    shadow(10.dp)
        .height(height)
        .fillMaxWidth()
        .background(
            color = Color.Transparent,
            shape = RoundedCornerShape(6.dp)
        )

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.postFooterModifier(height: Dp) =
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