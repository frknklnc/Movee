package com.example.movee.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.movee.R
import com.example.movee.uimodels.NowPlayingMovieUiModel
import com.example.movee.uimodels.PopularMovieUiModel
import com.example.movee.ui.components.MovieDateItem
import com.example.movee.ui.components.MovieRateItem
import com.example.movee.ui.viewmodel.MainViewModel
import com.example.movee.util.Constants.IMAGE_BASE
import com.example.movee.navigation.Route


@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel = hiltViewModel()) {
    Surface(modifier = Modifier.fillMaxSize()) {

        Column {

            val nowPlayingMoviesList = viewModel.nowPlayingMoviesList.collectAsState()

            val popularMoviesList = viewModel.popularMoviesList.collectAsState()
            MovieListView(popularMovies = popularMoviesList, navController = navController, nowPlayingMovies = nowPlayingMoviesList)

        }
    }
}

@Composable
fun MovieListView(popularMovies: State<List<PopularMovieUiModel>>, nowPlayingMovies: State<List<NowPlayingMovieUiModel>>, navController: NavController) {
    LazyColumn(contentPadding = PaddingValues(10.dp)) {
        item {
            Text(text = "Now Playing Movies",
                modifier = Modifier
                    .padding(vertical = 8.dp ))
        }
        item {
            LazyRow() {
                items(nowPlayingMovies.value) { movies ->
                    NowPlayingMovieRow(movies = movies, onMovieClicked = { id ->
                        navController.navigate(Route.DetailScreen.route + "/${movies.movieId}")
                    })
                    Spacer(modifier = Modifier.padding(5.dp))
                }
            }
        }
        item {
            Text(text = "Popular Movies",
                modifier = Modifier
                    .padding(vertical = 8.dp ))
        }

        items(popularMovies.value) { movies ->
            PopularMovieRow(movies = movies, onMovieClicked = { id ->
                navController.navigate(Route.DetailScreen.route + "/${movies.movieId}")
            })
            Spacer(modifier = Modifier.padding(5.dp))
        }
    }
}


@Composable
fun NowPlayingMovieRow(movies: NowPlayingMovieUiModel, onMovieClicked: (Int) -> Unit) {
    Surface(color = colorResource(id = R.color.milk), modifier = Modifier
        .fillMaxSize()
        .clickable { onMovieClicked.invoke(movies.movieId) },
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(modifier = Modifier.width(380.dp)) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current).data(IMAGE_BASE + movies.posterPath)
                        .placeholder(android.R.drawable.dialog_frame).crossfade(true).build()
                ),
                contentDescription = movies.title,
                modifier = Modifier
                    .size(height = 120.dp, width = 80.dp) ,
                contentScale = ContentScale.Fit
            )
            Column(modifier = Modifier
                .height(height = 120.dp)
                .fillMaxWidth(), verticalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = movies.title,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    fontSize = 16.sp, fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    MovieDateItem(date = movies.releaseDate, textColor = colorResource(id = R.color.black))
                    MovieRateItem(rate = movies.voteAverage.toString())
                }
            }
        }
    }
}


@Composable
fun PopularMovieRow(movies: PopularMovieUiModel, onMovieClicked: (Int) -> Unit) {
    Surface(color = colorResource(id = R.color.milk), modifier = Modifier
        .fillMaxWidth()
        .clickable { onMovieClicked.invoke(movies.movieId) },
        shape = RoundedCornerShape(5.dp)
    ) {
            Row {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current).data(IMAGE_BASE + movies.posterPath)
                            .placeholder(android.R.drawable.dialog_frame).crossfade(true).build()
                    ),
                    contentDescription = movies.title,
                    modifier = Modifier
                        .size(height = 120.dp, width = 80.dp) ,
                        contentScale = ContentScale.Fit
                )
                Column(modifier = Modifier
                    .height(height = 120.dp)
                    .fillMaxWidth(), verticalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = movies.title,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        fontSize = 16.sp, fontWeight = FontWeight.Bold
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        MovieDateItem(date = movies.releaseDate, textColor = colorResource(id = R.color.black))
                        MovieRateItem(rate = movies.voteAverage.toString())
                    }

                }
            }

    }
}

@Preview
@Composable
fun Preview() {
    val tempMovie = PopularMovieUiModel(
        movieId = 1,
        title = "godfather",
        releaseDate = "20.06.2995",
        posterPath = "sdjfj",
        voteAverage = 7.9

    )
PopularMovieRow(movies = tempMovie, onMovieClicked = {})
}