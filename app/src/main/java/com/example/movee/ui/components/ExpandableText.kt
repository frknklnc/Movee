package com.example.movee.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.movee.util.Constants.MINIMIZED_MAX_LINES
import com.example.movee.R


@Composable
fun ExpandableText(
    modifier: Modifier = Modifier,
    longText: String,
    minimizedMaxLines: Int = 4,
    textAlign: TextAlign = TextAlign.Start,
    expandHint: String = "… Show More",
    shrinkHint: String = "… Show Less",
    clickColor: Color = Color.Unspecified
) {
    var isExpanded by remember { mutableStateOf(value = false) }
    var textLayoutResultState by remember { mutableStateOf<TextLayoutResult?>(value = null) }
    var adjustedText by remember { mutableStateOf(value = longText) }
    val overflow = textLayoutResultState?.hasVisualOverflow ?: false
    val showOverflow = remember { mutableStateOf(value = false) }
    val showMore = " $expandHint"
    val showLess = " $shrinkHint"

    LaunchedEffect(textLayoutResultState) {
        if (textLayoutResultState == null) return@LaunchedEffect
        if (!isExpanded && overflow) {
            showOverflow.value = true
            val lastCharIndex =
                textLayoutResultState!!.getLineEnd(lineIndex = minimizedMaxLines - 1)
            adjustedText = longText
                .substring(startIndex = 0, endIndex = lastCharIndex)
                .dropLast(showMore.length)
                .dropLastWhile { it == ' ' || it == '.' }
        }
    }
    val annotatedText = buildAnnotatedString {
        if (isExpanded) {
            append(longText)
            withStyle(
                style = SpanStyle(
                    color = colorResource(id = R.color.mainColor),
                    fontSize = 16.sp
                )
            ) {
                pushStringAnnotation(tag = "showLess", annotation = "showLess")
                append(showLess)
                addStyle(
                    style = SpanStyle(
                        color = clickColor,
                        fontSize = 14.sp
                    ),
                    start = longText.length,
                    end = longText.length + showMore.length
                )
                pop()
            }
        } else {
            append(adjustedText)
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 14.sp
                )
            ) {
                if (showOverflow.value) {
                    pushStringAnnotation(tag = "showMore", annotation = "showMore")
                    append(showMore)
                    addStyle(
                        style = SpanStyle(
                            color = clickColor,
                            fontSize = 14.sp
                        ),
                        start = adjustedText.length,
                        end = adjustedText.length + showMore.length
                    )
                    pop()
                }
            }
        }

    }
    Box(modifier = modifier) {
        ClickableText(
            text = annotatedText,
            style = (MaterialTheme.typography.body1.copy(textAlign = textAlign)),
            maxLines = if (isExpanded) Int.MAX_VALUE else MINIMIZED_MAX_LINES,
            onTextLayout = { textLayoutResultState = it },
            onClick = { offset ->
                annotatedText.getStringAnnotations(
                    tag = "showLess",
                    start = offset,
                    end = offset + showLess.length
                ).firstOrNull()?.let {
                    isExpanded = !isExpanded
                }
                annotatedText.getStringAnnotations(
                    tag = "showMore",
                    start = offset,
                    end = offset + showMore.length
                ).firstOrNull()?.let {
                    isExpanded = !isExpanded
                }
            }
        )
    }
}