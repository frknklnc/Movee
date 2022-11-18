package com.example.movee.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movee.R

@Composable
fun ChipItem(
    modifier: Modifier = Modifier,
    backgroundColor: Color = colorResource(id = R.color.green),
    textColor: Color = colorResource(id = R.color.mainColor),
    string: String
) {

    Surface(modifier = modifier, shape = RoundedCornerShape(10.dp), color = backgroundColor) {

        Text(
            text = string,
            color = textColor,
            fontSize = 14.sp,
            modifier = Modifier.padding(vertical = 2.dp, horizontal = 6.dp)
        )
    }

}

@Preview
@Composable
fun ChipItemPreview() {
    ChipItem(string = "Seasons: 8")

}