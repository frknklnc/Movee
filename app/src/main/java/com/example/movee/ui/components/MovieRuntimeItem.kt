package com.example.movee.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movee.R

@Composable
fun MovieRuntimeItem(modifier: Modifier = Modifier,
                     textColor: Color = colorResource(id = R.color.green),
                     runtime: String) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painterResource(id = R.drawable.ic_clock),
            contentDescription = null, modifier = Modifier.padding(end = 2.dp), tint = textColor )

        Text(text = runtime + " min", fontSize = 14.sp, color = textColor)

    }

}

@Preview
@Composable
fun MovieRuntimeItemPreview() {
    MovieRuntimeItem(runtime = "500")

}