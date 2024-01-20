package com.example.medinfo.ui.composable

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MedInfoPagerIndicator(
    totalPages: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
    indicatorWidth: Dp = 6.dp,
    spacing: Dp = 2.dp,
    selectedMultiplier: Int = 3
) {
    assert(
        value = currentPage in 0 until totalPages,
        lazyMessage = { "vot pizdec ;d" }
    )

    val rowWidth =
        (indicatorWidth * (selectedMultiplier + (totalPages - 1))) +
                (spacing * (totalPages - 1))

    Row(
        modifier = modifier.requiredWidth(rowWidth),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing)
    ) {
        for (i in 0 until totalPages) {
            val selected = i == currentPage
            val height: Dp by animateIndicatorHeightAsState(selected)
            val width: Dp by animateIndicatorWidthAsState(selected)
            val color = indicatorColor(selected)
            val radius = indicatorRadius(selected)

            Indicator(
                width = width,
                height = height,
                color = color,
                radius = radius
            )
        }
    }
}

@Composable
fun Indicator(width: Dp, height: Dp, color: Color, radius: CornerRadius) {
    Canvas(
        modifier = Modifier.size(width, height),
        onDraw = {
            drawRoundRect(
                color = color,
                cornerRadius = radius,
                size = Size(width.toPx(), height.toPx())
            )
        }
    )
}

@Composable
fun animateIndicatorHeightAsState(
    selected: Boolean,
    indicatorHeight: Dp = 2.dp,
): State<Dp> = animateDpAsState(
    targetValue = if (selected) 4.dp else indicatorHeight,
    animationSpec = pagerIndicatorAnimSpec,
    label = ""
)

@Composable
fun animateIndicatorWidthAsState(
    selected: Boolean,
    indicatorWidth: Dp = 6.dp,
): State<Dp> = animateDpAsState(
    targetValue = if (selected) 8.dp else indicatorWidth,
    animationSpec = pagerIndicatorAnimSpec,
    label = ""
)

fun indicatorColor(selected: Boolean) =
    if (selected) Color(0xFF028FF6) else Color.White

fun indicatorRadius(selected: Boolean) =
    CornerRadius(if (selected) 2f else 1f)

val pagerIndicatorAnimSpec = tween<Dp>(easing = LinearOutSlowInEasing)