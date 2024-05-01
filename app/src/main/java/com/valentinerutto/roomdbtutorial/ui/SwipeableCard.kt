package com.valentinerutto.roomdbtutorial.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SwipeableCard(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Text(text = "SwipeableCard")
    }
}

@Preview(name = "SwipeableCard")
@Composable
private fun PreviewSwipeableCard() {
    SwipeableCard()
}