package com.example.movee.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
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
fun MovieRateItem(modifier: Modifier = Modifier,
                  backgroundColor: Color = colorResource(id = R.color.green),
                  textColor : Color = colorResource(id = R.color.mainColor),
                  rate: String) {

    Surface(modifier = modifier, shape = RoundedCornerShape(10.dp), color = backgroundColor) {

        Row(
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(painterResource(
                id = R.drawable.ic_star),
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.size(15.dp))

            Text(text = rate, color = textColor, fontSize = 14.sp)

        }

    }

}

@Preview
@Composable
fun MovieRateItemPreview() {
    MovieRateItem(rate = "8")
    
}