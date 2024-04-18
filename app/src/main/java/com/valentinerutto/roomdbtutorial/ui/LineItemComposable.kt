package com.valentinerutto.roomdbtutorial.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valentinerutto.roomdbtutorial.data.local.PickuplineEntity

@Composable
fun LineItemComposable(modifier: Modifier, entity: PickuplineEntity) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
        ) {
            Spacer(modifier = Modifier.padding(2.dp))
            Text(text = entity.text, fontSize = 16.sp)
            Spacer(modifier = Modifier.padding(1.dp))
        }

    }
}