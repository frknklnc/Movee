package com.example.movee.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.movee.navigation.Route
import com.example.movee.ui.components.MovieRateItem
import com.example.movee.ui.components.TextItem
import com.example.movee.ui.viewmodel.TvSeriesViewModel
import com.example.movee.uimodels.tvseries.PopularTvSeriesUiModel
import com.example.movee.uimodels.tvseries.TopRatedTvSeriesUiModel
import com.example.movee.util.Constants
import com.example.movee.R

@Composable
fun TvSeriesScreen(
    navController: NavController, viewModel: TvSeriesViewModel = hiltViewModel()
) {

    val topRatedTvSeriesList = viewModel.topRatedTvSeriesList.collectAsState()

    val popularTvSeriesList = viewModel.popularTvSeriesList.collectAsState()

    TvSeriesListView(
        popularTvSeries = popularTvSeriesList.value,
        topRatedTvSeries = topRatedTvSeriesList,
        navController = navController
    )
}

@Composable
fun TvSeriesListView(
    popularTvSeries: List<PopularTvSeriesUiModel>,
    topRatedTvSeries: State<List<TopRatedTvSeriesUiModel>>,
    navController: NavController
) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {

        TextItem(text = "Popular Tv Series",
            Modifier.padding(vertical = 8.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold)

        LazyRow {
            items(popularTvSeries) { tvSeries ->
                PopularTvSeriesRow(tvSeries = tvSeries, onMovieClicked = { id ->
                    navController.navigate(Route.TvSeriesDetailScreen.route + "/${tvSeries.tvSeriesId}")

                })
                Spacer(modifier = Modifier.padding(5.dp))
            }
        }

        TextItem(text = "Top Rated Tv Series",
            Modifier.padding(vertical = 8.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold)

        LazyVerticalGrid(columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            content = {

                items(topRatedTvSeries.value.size) { ind ->
                    TvSeriesGridItem(tvSeries = topRatedTvSeries.value[ind],
                        navController = navController,
                        onMovieClicked = { id ->
                            navController.navigate(Route.TvSeriesDetailScreen.route + "/${id}")
                        })
                }
            })
    }
}


@Composable
fun PopularTvSeriesRow(tvSeries: PopularTvSeriesUiModel, onMovieClicked: (Int) -> Unit) {
    Surface(
        modifier = Modifier.clickable { onMovieClicked.invoke(tvSeries.tvSeriesId) },
        shape = RoundedCornerShape(5.dp),
        elevation = 5.dp
    ) {
        Row(modifier = Modifier.width(250.dp)) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(Constants.IMAGE_BASE + tvSeries.posterPath)
                        .placeholder(android.R.drawable.dialog_frame).crossfade(true).build()
                ),
                contentDescription = tvSeries.title,
                modifier = Modifier.size(height = 120.dp, width = 80.dp),
                contentScale = ContentScale.Fit
            )
            Column(
                modifier = Modifier
                    .height(height = 120.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = tvSeries.title,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    MovieRateItem(rate = tvSeries.voteAverage.toString())
                }
            }
        }
    }
}


@Composable
fun TvSeriesGridItem(
    tvSeries: TopRatedTvSeriesUiModel, navController: NavController, onMovieClicked: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onMovieClicked.invoke(tvSeries.tvSeriesId) },
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(Constants.IMAGE_BASE + tvSeries.posterPath)
                        .placeholder(R.drawable.image_place_holder)
                        .crossfade(true).build()
                ),
                contentDescription = tvSeries.title,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            MovieRateItem(
                rate = tvSeries.voteAverage.toString(),
                modifier = Modifier.padding(top =  5.dp))
            Text(
                text = tvSeries.title,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = colorResource(id = R.color.mainColor)
            )


        }

    }

}


