package com.valentinerutto.roomdbtutorial.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pickuplines")
data class PickuplineEntity(

    @PrimaryKey(autoGenerate = true) val idKey: Int =0 , val category: String, val text: String)