package com.orlandev.testmobile.utils

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImagePainter
import coil.compose.rememberImagePainter
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer

@Composable
fun ShimmerImage(
    imgUrl: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    colorShimmer: Color = Color.Gray
) {
    val painter =
        rememberImagePainter(
            data = imgUrl,
            builder = {
                crossfade(700)
            })

    val state = painter.state is AsyncImagePainter.State.Loading
    Image(
        modifier = modifier
            .placeholder(
                color = colorShimmer,
                visible = state,
                highlight = PlaceholderHighlight.shimmer(highlightColor = Color.White)
            ),
        painter = painter,
        contentScale = ContentScale.Crop,
        contentDescription = contentDescription,
    )
}