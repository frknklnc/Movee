package com.example.movee.ui.view

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.movee.ui.components.MovieDateItem
import com.example.movee.ui.components.MovieRateItem
import com.example.movee.ui.components.MovieRuntimeItem
import com.example.movee.ui.viewmodel.DetailViewModel
import com.example.movee.util.Constants

@Composable
fun DetailScreen(navController: NavController,
                 scrollState: ScrollState,
                 viewModel: DetailViewModel = hiltViewModel()) {

    val movieDetails = viewModel.movieDetails.collectAsState()

    movieDetails.value?.let {movieDetails->

        Column(modifier = Modifier.verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(Constants.IMAGE_BASE + movieDetails.posterPath)
                        .placeholder(R.drawable.dialog_frame).crossfade(true).build()
                ),
                contentDescription = movieDetails.title,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 12.dp),
                contentScale = ContentScale.Crop
            )
            //Spacer(modifier = Modifier.padding(8.dp))

            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 32.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = movieDetails.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    MovieRateItem(rate = movieDetails.voteAverage.toString())
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text = movieDetails.genre)

                Row(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)) {
                    MovieRuntimeItem(runtime = movieDetails.runtime.toString())
                    Spacer(modifier = Modifier.padding(10.dp))
                    MovieDateItem(date = movieDetails.releaseDate, textColor = colorResource(id = com.example.movee.R.color.green))
                }

                Text(text = movieDetails.overview)

            }
        }


    }
}

