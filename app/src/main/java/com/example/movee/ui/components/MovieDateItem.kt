package com.example.movee.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.movee.R

@Composable
fun MovieDateItem(modifier: Modifier = Modifier,
                  textColor: Color = colorResource(id = R.color.mainColor),
                  date: String) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painterResource(id = R.drawable.ic_date),
                contentDescription = null, tint = textColor)

            Text(text = date, fontSize = 14.sp, color = textColor)

        }

}

@Preview
@Composable
fun MovieDateItemPreview() {
    MovieDateItem(date = "5")

}