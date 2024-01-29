package com.example.medinfo.ui.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.medinfo.domain.model.Post
import com.example.medinfo.ui.theme.textStyle14
import com.example.medinfo.ui.theme.textStyle20

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MedInfoPagerWithIndicator(
    items: List<Post>?
) {

    val pagerState = rememberPagerState()

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .collect { pagerState.animateScrollToPage(it) }
    }

    items?.let {
        Box {
            HorizontalPager(
                pageCount = items.size,
                key = { it },
                state = pagerState,
                pageContent = { HeaderPagerItem(items[it]) }
            )
            MedInfoPagerIndicator(
                totalPages = items.size,
                currentPage = pagerState.currentPage,
                modifier = Modifier.padding(bottom = 16.dp).align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
fun HeaderPagerItem(item: Post) {
    Box {
        HeaderImage(item.image)
        HeaderLabel(Modifier.padding(start = 16.dp, bottom = 40.dp))
    }
}

@Composable
fun HeaderImage(image: Int) {
    Image(
        painter = painterResource(image),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(175.dp)
    )
}

@Composable
fun BoxScope.HeaderLabel(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.align(Alignment.BottomStart)
    ) {
        HeaderTitle()
        HeaderButton()
    }
}

@Composable
fun HeaderButton() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.headerButton()
    ) {
        Text(
            text = "მეტი",
            style = textStyle14,
            color = Color.White,
        )
    }
}

@Composable
fun HeaderTitle() {
    Text(
        text = "სათაური",
        style = textStyle20,
        color = Color.White,
    )
}

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.headerButton() =
    height(32.dp)
        .width(72.dp)
        .background(
            color = Color(0xFF028FF6),
            shape = RoundedCornerShape(6.dp)
        )