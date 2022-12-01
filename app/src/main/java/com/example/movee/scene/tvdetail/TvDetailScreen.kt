package com.example.movee.scene.tvdetail


import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.example.movee.navigation.Route
import com.example.movee.ui.components.ChipItem
import com.example.movee.ui.components.MovieRateItem
import com.example.movee.ui.components.TextItem
import com.example.movee.ui.view.CardImageItem
import com.example.movee.ui.view.PosterImageItem
import com.example.movee.uimodels.CreditUiModel
import com.example.movee.util.round

@Composable
fun TvDetailScreen(
    navController: NavController,
    scrollState: ScrollState,
    viewModel: TvDetailViewModel = hiltViewModel()
) {

    val uiState = viewModel.uiState.collectAsState()

    uiState.value?.let { state ->
        Column(
            modifier = Modifier.verticalScroll(scrollState),
        ) {
            if (state.tvDetailUiModel != null) {

                PosterImageItem(imagePath = state.tvDetailUiModel.backdropPath, Modifier.padding(bottom = 8.dp))

                Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {

                    TextItem(
                        text = state.tvDetailUiModel.title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        ChipItem(string = "Seasons: " + state.tvDetailUiModel.numberOfSeasons.toString())
                        ChipItem(string = "Episodes: " + state.tvDetailUiModel.numberOfEpisodes.toString())
                        MovieRateItem(
                            rate = round(state.tvDetailUiModel.voteAverage) + " " + "(${state.tvDetailUiModel.voteCount})"
                        )
                    }
                    TextItem(
                        text = state.tvDetailUiModel.overview,
                        Modifier.padding(bottom = 10.dp)
                    )
                }
            }
            TextItem(
                text = "Cast",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            LazyRow(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(state.castList.size) { index ->
                    TvSeriesCredits(
                        cast = state.castList[index],
                        onClick = { id ->
                            navController.navigate(Route.CastScreen.route + "/${id}", navOptions {
                                restoreState = false
                            })
                        })
                }
            }
        }
    }
}

@Composable
fun TvSeriesCredits(
    cast: CreditUiModel,
    onClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .width(100.dp)
            .fillMaxHeight()
            .clickable { onClick.invoke(cast.castId) },
        shape = RoundedCornerShape(5.dp),
        elevation = 5.dp
    ) {
        Column {

            CardImageItem(imagePath = cast.profilePath)

            TextItem(
                text = cast.originalName,
                modifier = Modifier.padding(horizontal = 3.dp, vertical = 5.dp),
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

