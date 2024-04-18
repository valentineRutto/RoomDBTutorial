package com.valentinerutto.roomdbtutorial.utils


sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()

    data class Error<T>(val errorMessage: String) : Resource<T>()

    class Loading<T> : Resource<T>()
}