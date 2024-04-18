package com.valentinerutto.roomdbtutorial.data.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class LineResponse : ArrayList<LineResponse.LineResponseItem>(){
    @Serializable
    data class LineResponseItem(
        @SerialName("category")
        val category: String?,
        @SerialName("comments")
        val comments: List<Any?>?,
        @SerialName("dateCreated")
        val dateCreated: String?,
        @SerialName("_id")
        val id: String?,
        @SerialName("language")
        val language: String?,
        @SerialName("ratings")
        val ratings: Ratings?,
        @SerialName("tags")
        val tags: List<Any?>?,
        @SerialName("text")
        val text: String?,
        @SerialName("usageExamples")
        val usageExamples: List<Any?>?,
        @SerialName("__v")
        val v: Int?
    ) {
        @Serializable
        data class Ratings(
            @SerialName("average")
            val average: Int?,
            @SerialName("count")
            val count: Int?
        )
    }
}