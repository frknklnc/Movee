package com.example.movee.ui.view

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movee.R
import com.example.movee.navigation.Route
import com.example.movee.ui.components.ExpandableText
import com.example.movee.ui.components.TextItem
import com.example.movee.ui.viewmodel.CastViewModel
import com.example.movee.uimodels.actor.CastCreditUiModel

@Composable
fun CastScreen(
    navController: NavController,
    scrollState: ScrollState,
    viewModel: CastViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    uiState.value?.let { state ->
        Column(
            modifier = Modifier.verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.actorDetailUiModel != null) {

                PosterImageItem(imagePath = state.actorDetailUiModel.profilePath)

                Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                    TextItem(
                        text = state.actorDetailUiModel.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    ExpandableText(
                        longText = state.actorDetailUiModel.biography, clickColor = colorResource(
                            id = R.color.black
                        )
                    )

                }
            }
            LazyRow(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                items(state.creditList.size) { index ->
                    ActorCredit(
                        credit = state.creditList[index],
                        onClick = { pair ->
                            when(pair.second){
                                "movie" -> {
                                    navController.navigate(Route.MovieDetailScreen.route + "/${pair.first}")

                                }
                                "tv" -> {
                                    navController.navigate(Route.TvDetailScreen.route + "/${pair.first}")

                                }
                            }
                        })
                }
            }
        }
    }
}

@Composable
fun ActorCredit(
    credit: CastCreditUiModel,
    onClick: (Pair<Int,String>) -> Unit
) {
    Card(
        modifier = Modifier
            .width(100.dp)
            .fillMaxHeight()
            .clickable { onClick.invoke(Pair(credit.id,credit.mediaType)) },
        shape = RoundedCornerShape(5.dp),
        elevation = 5.dp
    ) {
        Column {

            CardImageItem(imagePath = credit.posterPath)

            Text(
                text = credit.originalTitle,
                modifier = Modifier.padding(horizontal = 3.dp, vertical = 5.dp),
                fontSize = 14.sp,
                color = colorResource(id = R.color.mainColor),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
