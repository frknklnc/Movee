package com.example.movee.scene.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.movee.ui.components.TextInputItem
import com.example.movee.ui.view.CardImageItem
import com.example.movee.uimodels.SearchUiModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavController
) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val queryState = viewModel.query.collectAsStateWithLifecycle()

    Column {
        TextInputItem(
            query = queryState.value,
            onValueChange = { str ->
                viewModel.handleQueryChange(str)
            }
        )

        LazyColumn() {
            items(uiState.value.data) { search ->
                SearchRow(search = search, onClick = {
                    navController.navigate(it)
                })
            }
        }
    }
}



@Composable
fun SearchRow(search: SearchUiModel, onClick: (String) -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick.invoke(search.navRoute) },
        shape = RoundedCornerShape(5.dp),
        elevation = 5.dp
    ) {
        Row {
            search.imagePath?.let { img ->
                CardImageItem(
                    imagePath = img
                )
            }
            Column(
                modifier = Modifier
                    .height(height = 120.dp)
                    .fillMaxWidth(), verticalArrangement = Arrangement.SpaceBetween
            ) {
                search.name?.let { str ->
                    Text(
                        text = str,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        fontSize = 16.sp, fontWeight = FontWeight.Bold
                    )
                }
                Image(
                    painter = rememberAsyncImagePainter(model = search.type),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}