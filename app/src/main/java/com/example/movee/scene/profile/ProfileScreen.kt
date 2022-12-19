package com.example.movee.scene.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.example.movee.data.repository.LoginState
import com.example.movee.navigation.Route
import com.example.movee.scene.movie.PopularMovieRow
import com.example.movee.ui.components.MovieRateItem
import com.example.movee.ui.view.CardImageItem
import com.example.movee.uimodels.favourite.FavouriteMovieUiModel

@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileViewModel = hiltViewModel()) {

    val accountDetail = viewModel.accountDetail.collectAsState()
    val favouriteMovie = viewModel.favouriteMovie.collectAsState()

    LaunchedEffect(key1 = viewModel.hasUser) {
        if (viewModel.hasUser == LoginState.LOGGED_IN) {
            viewModel.loadAccountDetails()
        }else{
            navController.navigate(Route.LoginScreen.route, navOptions{
                popUpTo(Route.LoginScreen.route){
                    inclusive = true
                }
            })
        }

    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        accountDetail.value?.let { detail ->
            Text(text = "Welcome\n" + detail.username)
            Text(text = detail.country)
        }

        Button(onClick = { navController.navigate(Route.MapScreen.route) }) {
            Text(text = "Map")

        }

        FavouriteListView(favoruiteMovie = favouriteMovie.value, navController = navController)

    }
}

@Composable
fun FavouriteListView(
    favoruiteMovie: List<FavouriteMovieUiModel>,
    navController: NavController
) {
    LazyColumn(){
        items(favoruiteMovie) { favourite ->
            FavouriteRow(movies = favourite, onClick = { id ->
                navController.navigate(Route.MovieDetailScreen.route + "/${favourite.movieId}")

            })
            Spacer(modifier = Modifier.padding(5.dp))
        }
    }

}

@Composable
fun FavouriteRow(movies: FavouriteMovieUiModel, onClick: (Int) -> Unit) {
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
                    MovieRateItem(rate = movies.voteAverage.toString())
                }

            }
        }

    }
}