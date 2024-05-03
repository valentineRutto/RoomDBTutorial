package com.valentinerutto.roomdbtutorial.data.local

import com.valentinerutto.roomdbtutorial.data.remote.LineResponse
import com.valentinerutto.roomdbtutorial.data.remote.RandomLineResponse

fun mapResponseToEntity(response: LineResponse?): List<PickuplineEntity> {
    return response!!.map {
        PickuplineEntity(
            category = it.category!!, text = it.text!!
        )
    }
}

fun mapResponseToEntity(response: RandomLineResponse?): PickuplineEntity {
    return PickuplineEntity(category = response?.category!!, text = response.text!!)
}