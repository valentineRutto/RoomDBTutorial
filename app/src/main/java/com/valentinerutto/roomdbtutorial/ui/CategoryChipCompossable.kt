package com.valentinerutto.roomdbtutorial.ui

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
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
import com.valentinerutto.roomdbtutorial.data.local.PickuplineEntity

@Composable
fun CategoryChipCompossable(
    items: List<PickuplineEntity>,
    defaultSelectedItemIndex: Int = 0,
    selectedItemIcon: ImageVector = Icons.Filled.Done,
    itemIcon: ImageVector = Icons.Filled.Check,
    onSelectedChanged: (String) -> Unit = {}
) {
    var selectedItemIndex by remember { mutableStateOf(defaultSelectedItemIndex) }

    LazyRow(userScrollEnabled = true) {

        items.distinctBy { it.category }

        items(items.size) { index: Int ->
            FilterChip(
                selected = items[selectedItemIndex] == items[index],
                onClick = {
                    selectedItemIndex = index
                    onSelectedChanged(items[index].category)
                },
                label = {
                    Text(items[index].category)
                },
                leadingIcon = if (items[selectedItemIndex] == items[index]) {
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
