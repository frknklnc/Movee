package com.example.movee.ui.view

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.movee.ui.theme.MoveeTheme
import com.example.movee.util.Constants

@Composable
fun PosterImageItem(imagePath: String, modifier: Modifier) {

    Image(
        painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(Constants.IMAGE_BASE + imagePath)
                .error(com.example.movee.R.drawable.image_place_holder)
                .placeholder(R.drawable.dialog_frame).crossfade(true).build()
        ),
        contentDescription = null,
        modifier.fillMaxSize(),
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
                .error(com.example.movee.R.drawable.image_place_holder)
                .placeholder(R.drawable.dialog_frame).crossfade(true).build()
        ),
        contentDescription = null,
        modifier = modifier,
        contentScale = contentScale
    )

}

@Preview
@Composable
fun ImageCardPrev() {
    MoveeTheme() {
        CardImageItem(imagePath = Constants.IMAGE_BASE + "/pFlaoHTZeyNkG83vxsAJiGzfSsa.jpg")
        
    }
}