package com.example.movee.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.movee.util.Constants
import android.R

@Composable
fun PosterImageItem(imagePath: String) {

    Image(
        painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(Constants.IMAGE_BASE + imagePath)
                .error(com.example.movee.R.drawable.image_place_holder).placeholder(R.drawable.dialog_frame).crossfade(true).build()
        ),
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun CardImageItem(
    imagePath: String, modifier: Modifier = Modifier.size(height = 150.dp, width = 100.dp),
    contentScale: ContentScale = ContentScale.Fit
) {
    Image(
        painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(Constants.IMAGE_BASE + imagePath)
                .error(com.example.movee.R.drawable.image_place_holder).placeholder(R.drawable.dialog_frame).crossfade(true).build()
        ),
        contentDescription = null,
        modifier = modifier,
        contentScale = contentScale
    )

}