package com.valentinerutto.roomdbtutorial.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.valentinerutto.roomdbtutorial.data.local.PickuplineEntity

@Composable
fun CategoryChipCompossable(
    items: List<PickuplineEntity>,
    defaultSelectedItemIndex: Int = 0,
    selectedItemIcon: ImageVector = Icons.Filled.Done,
    onSelectedChanged: (String) -> Unit = {}
) {
    var selectedItemIndex by remember { mutableStateOf(defaultSelectedItemIndex) }

    LazyRow(userScrollEnabled = true) {

        val linesCategory = items.distinctBy { it.category }

        items(linesCategory.size) { index: Int ->
            FilterChip(
                modifier = Modifier.padding(8.dp),
                selected = linesCategory[selectedItemIndex] == linesCategory[index],
                onClick = {
                    selectedItemIndex = index
                    onSelectedChanged(linesCategory[index].category)
                },
                label = {
                    Text(linesCategory[index].category)
                },
                leadingIcon = if (linesCategory[selectedItemIndex] == linesCategory[index]) {
                    {
                        Icon(
                            imageVector = selectedItemIcon,
                            contentDescription = "Localized description",
                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                        )
                    }
                } else {
                    null
                },
            )
        }
    }
}
