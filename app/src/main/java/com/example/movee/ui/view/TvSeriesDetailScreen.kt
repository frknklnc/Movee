package com.example.movee.ui.view


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.node.modifierElementOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.movee.R
import com.example.movee.ui.components.ChipItem
import com.example.movee.ui.components.MovieRateItem
import com.example.movee.ui.components.TextItem
import com.example.movee.ui.viewmodel.TvSeriesDetailViewModel
import com.example.movee.util.Constants

@Composable
fun TvSeriesDetailScreen(
    navController: NavController,
    scrollState: ScrollState,
    viewModel: TvSeriesDetailViewModel = hiltViewModel()
) {

    val tvSeriesDetail = viewModel.tvSeriesDetails.collectAsState()

    tvSeriesDetail.value?.let { detail ->

        Column(
            modifier = Modifier.verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(Constants.IMAGE_BASE + detail.posterPath)
                        .placeholder(R.drawable.image_place_holder).crossfade(true).build()
                ),
                contentDescription = detail.title,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )

                Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 4.dp, top = 8.dp), horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TextItem(text = detail.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        MovieRateItem(rate = detail.voteAverage.toString())
                    }
                    Row(modifier = Modifier.padding(vertical = 8.dp)) {
                        ChipItem(string = "Seasons: " + detail.numberOfSeasons.toString())
                        Spacer(modifier = Modifier.padding(5.dp))
                        ChipItem(string = "Episodes: " + detail.numberOfEpisodes.toString())
                    }
                    TextItem(text = detail.overview, Modifier.padding(bottom = 10.dp))
            }
        }
    }
}
