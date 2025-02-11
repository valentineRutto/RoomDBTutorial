package com.valentinerutto.roomdbtutorial.ui

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.valentinerutto.roomdbtutorial.data.local.PickuplineEntity
import com.valentinerutto.roomdbtutorial.random
import org.koin.androidx.compose.koinViewModel

@Composable
fun LineItemComposable(modifier: Modifier, entity: PickuplineEntity) {
    val vm = koinViewModel<LineViewModel>()
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth().background(Color.random())
            .wrapContentSize(align = Alignment.Center)
            .height(300.dp), shape = RoundedCornerShape(16.dp)

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.padding(4.dp))
            Text(text = entity.text, fontSize = 16.sp)
            Spacer(modifier = Modifier.padding(4.dp))
            Button(onClick = {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, entity.text)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(context,shareIntent, null)

            }) {
                Icon(imageVector = Icons.Default.Share, contentDescription = null)

                Text(text = "Share", fontSize = 16.sp)
            }
        }


    }
}
