package com.example.movee.scene.tv

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movee.navigation.Route
import com.example.movee.scene.movie.calculateCurrentOffsetForPage
import com.example.movee.ui.components.MovieRateItem
import com.example.movee.ui.components.TextItem
import com.example.movee.ui.view.CardImageItem
import com.example.movee.uimodels.tv.PopularTvUiModel
import com.example.movee.uimodels.tv.TopRatedTvUiModel
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import kotlin.math.absoluteValue

@Composable
fun TvScreen(
    navController: NavController, scrollState: ScrollState, viewModel: TvViewModel = hiltViewModel()
) {

    val topRatedTvSeriesList = viewModel.topRatedTvSeriesList.collectAsState()

    val popularTvSeriesList = viewModel.popularTvSeriesList.collectAsState()

    TvSeriesListView(
        popularTvSeries = popularTvSeriesList.value,
        topRatedTvSeries = topRatedTvSeriesList.value,
        navController = navController,
        scrollState = scrollState
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TvSeriesListView(
    popularTvSeries: List<PopularTvUiModel>,
    topRatedTvSeries: List<TopRatedTvUiModel>,
    scrollState: ScrollState,
    navController: NavController
) {

    LazyColumn {
        item {
            TextItem(
                text = "Popular Tv Series",
                Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        item {
            HorizontalPager(
                count = popularTvSeries.size,
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
                                start = 0.5f, stop = 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                        }
                        .fillMaxWidth()
                        .clickable {
                            popularTvSeries[page].tvSeriesId
                            navController.navigate(Route.TvDetailScreen.route + "/${popularTvSeries[page].tvSeriesId}")
                        }
                        .aspectRatio(0.6655f)) {
                    CardImageItem(
                        imagePath = popularTvSeries[page].posterPath,
                        modifier = Modifier.fillMaxSize(),
                        ContentScale.Crop
                    )
                }
            }
        }
        item {
            TextItem(
                text = "Top Rated Tv Series",
                Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        item {
            Flow(topRatedTvSeries = topRatedTvSeries, onClick = { id ->
                navController.navigate(Route.TvDetailScreen.route + "/${id}")
            })
        }

    }
}


@Composable
fun Flow(topRatedTvSeries: List<TopRatedTvUiModel>, onClick: (Int) -> Unit) {

    val itemSize: Dp = LocalConfiguration.current.screenHeightDp.dp / 3

    FlowRow(mainAxisSize = SizeMode.Expand, mainAxisAlignment = FlowMainAxisAlignment.SpaceEvenly) {
        topRatedTvSeries.forEachIndexed { index, _ ->
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(12.dp)
                    .clickable { onClick.invoke(topRatedTvSeries[index].tvSeriesId) },
                elevation = 5.dp,
                shape = RoundedCornerShape(5.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    CardImageItem(
                        imagePath = topRatedTvSeries[index].posterPath,
                        modifier = Modifier.size(itemSize),
                        contentScale = ContentScale.Crop
                    )
                    MovieRateItem(
                        rate = topRatedTvSeries[index].voteAverage.toString(),
                        modifier = Modifier.padding(top = 5.dp)
                    )
                    TextItem(
                        text = topRatedTvSeries[index].title,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}


