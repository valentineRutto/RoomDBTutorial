package com.valentinerutto.roomdbtutorial.data.local

import com.valentinerutto.roomdbtutorial.data.remote.LineResponse
import com.valentinerutto.roomdbtutorial.data.remote.RandomLineResponse
import java.util.UUID
import kotlin.random.Random

fun mapResponseToEntity(response: LineResponse?): List<PickuplineEntity>? {
    return response?.map {
        PickuplineEntity(
            id = it.id!!, text = it.text!!)

    }
}

fun mapResponseToEntity(response: RandomLineResponse?): PickuplineEntity {
    return PickuplineEntity(id = response?.id, text = response?.text)
}