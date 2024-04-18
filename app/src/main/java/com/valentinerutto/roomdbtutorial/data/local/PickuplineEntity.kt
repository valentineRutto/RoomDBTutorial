package com.valentinerutto.roomdbtutorial.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pickuplines")
data class PickuplineEntity(
    @PrimaryKey val id: String, val text: String
)