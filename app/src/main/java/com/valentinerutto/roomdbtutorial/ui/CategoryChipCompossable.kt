package com.valentinerutto.roomdbtutorial.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.valentinerutto.roomdbtutorial.data.local.PickuplineEntity

@Composable
fun CategoryChipCompossable(category: String,lines: List<PickuplineEntity>) {
    var selected by remember { mutableStateOf(false) }

    FilterChip(
        onClick = { selected = !selected
                  val lines = lines.filter { it.category == category }
                  },
        label = {
            Text(category)
        },
        selected = selected,
        leadingIcon = if (selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else {
            null
        },
    )
}