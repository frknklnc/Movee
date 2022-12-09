package com.example.movee.scene.moviedetail

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movee.navigation.Route
import com.example.movee.ui.components.MovieDateItem
import com.example.movee.ui.components.MovieRateItem
import com.example.movee.ui.components.MovieRuntimeItem
import com.example.movee.ui.components.TextItem
import com.example.movee.ui.view.CardImageItem
import com.example.movee.ui.view.PosterImageItem
import com.example.movee.uimodels.CreditUiModel
import com.example.movee.util.round

@Composable
fun MovieDetailScreen(
    navController: NavController,
    scrollState: ScrollState,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {

    val uiState = viewModel.uiState.collectAsState()


    uiState.value?.let { state ->

        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            if (state.movieDetailUiModel != null) {
                PosterImageItem(
                    imagePath = state.movieDetailUiModel.backdropPath,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {

                    Text(
                        text = state.movieDetailUiModel.title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                    //Spacer(modifier = Modifier.padding(5.dp))

                    TextItem(text = state.movieDetailUiModel.genre)

                    Row(
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        MovieRuntimeItem(runtime = state.movieDetailUiModel.runtime.toString())
                        Spacer(modifier = Modifier.padding(10.dp))
                        MovieDateItem(date = state.movieDetailUiModel.releaseDate)
                        Spacer(modifier = Modifier.padding(10.dp))
                        MovieRateItem(rate = round(state.movieDetailUiModel.voteAverage) + " " + "(${state.movieDetailUiModel.voteCount})")
                    }
                    TextItem(
                        text = state.movieDetailUiModel.overview
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
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(state.castList.size) { index ->
                    MoviesCredits(
                        cast = state.castList[index],
                        onClick = { id ->
                            navController.navigate(Route.CastScreen.route + "/${id}")
                        })
                }
            }
        }

    }
}

@Composable
fun MoviesCredits(
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