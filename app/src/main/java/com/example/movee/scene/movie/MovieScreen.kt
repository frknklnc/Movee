package com.example.movee.scene.movie

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.movee.R
import com.example.movee.navigation.Route
import com.example.movee.ui.components.MovieDateItem
import com.example.movee.ui.components.MovieRateItem
import com.example.movee.ui.components.TextItem
import com.example.movee.ui.view.CardImageItem
import com.example.movee.uimodels.movie.NowPlayingMovieUiModel
import com.example.movee.uimodels.movie.PopularMovieUiModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerScope
import kotlin.math.absoluteValue

@Composable
fun MoviesScreen(
    navController: NavController,
    viewModel: MovieViewModel = hiltViewModel()
) {

    val nowPlayingMoviesList = viewModel.nowPlayingMoviesList.collectAsState()
    //val popularMoviesList = viewModel.popularMoviesList.collectAsState()
    val popularMoviesList = viewModel.popularMovies.collectAsLazyPagingItems()
    val popularMovieState = rememberLazyListState()

    MovieListView(
        popularMovies = popularMoviesList,
        navController = navController,
        nowPlayingMovies = nowPlayingMoviesList.value,
        popularMovieState = popularMovieState
    )
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun MovieListView(
    popularMovies: LazyPagingItems<PopularMovieUiModel>,
    nowPlayingMovies: List<NowPlayingMovieUiModel>,
    popularMovieState: LazyListState,
    navController: NavController
) {
    LazyColumn(state = popularMovieState){
        item {
            TextItem(
                text = "Now Playing Movies",
                Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            HorizontalPager(
                count = nowPlayingMovies.size,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 85.dp)
            ) { page ->
                Card(
                    Modifier
                        .graphicsLayer {
                            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                            lerp(
                                start = 0.65f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            ).also { scale ->
                                scaleX = scale
                                scaleY = scale
                            }
                            alpha = lerp(
                                start = 0.5f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                        }
                        .fillMaxWidth()
                        .clickable {
                            nowPlayingMovies[page].movieId
                            navController.navigate(Route.MovieDetailScreen.route + "/${nowPlayingMovies[page].movieId}")
                        }
                        .aspectRatio(0.6655f)
                ) {
                    CardImageItem(
                        imagePath = nowPlayingMovies[page].posterPath,
                        modifier = Modifier.fillMaxSize(), ContentScale.Crop
                    )

                }
            }
        }
        item {
            TextItem(
                text = "Popular Movies",
                Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        if(popularMovies.loadState.refresh == LoadState.Loading || popularMovies.loadState.append == LoadState.Loading){
            item  {
                Spacer(Modifier.height(50.dp))

                CircularProgressIndicator(
                    modifier = Modifier.size(100.dp),
                    strokeWidth = 6.dp,
                    color = Color.Gray
                )

                Spacer(Modifier.height(50.dp))
            }
        }
        items(popularMovies) { movies ->
            movies?.let {
                PopularMovieRow(movies = movies, onClick = { id ->
                    navController.navigate(Route.MovieDetailScreen.route + "/${movies.movieId}")
                })
                Spacer(modifier = Modifier.padding(5.dp))
            }
        }
    }
}


@Composable
fun PopularMovieRow(movies: PopularMovieUiModel, onClick: (Int) -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { onClick.invoke(movies.movieId) },
        shape = RoundedCornerShape(5.dp),
        elevation = 5.dp
    ) {
        Row {

            CardImageItem(
                imagePath = movies.posterPath,
                modifier = Modifier.size(height = 120.dp, width = 80.dp)
            )
            Column(
                modifier = Modifier
                    .height(height = 120.dp)
                    .fillMaxWidth(), verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = movies.title,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    fontSize = 16.sp, fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    MovieDateItem(
                        date = movies.releaseDate,
                        textColor = colorResource(id = R.color.black)
                    )
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
    PopularMovieRow(movies = tempMovie, onClick = {})
}

@ExperimentalPagerApi
fun PagerScope.calculateCurrentOffsetForPage(page: Int): Float {
    return (currentPage - page) + currentPageOffset
}
