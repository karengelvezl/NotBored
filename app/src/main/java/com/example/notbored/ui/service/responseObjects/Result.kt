package com.example.notbored.ui.service.responseObjects

sealed class Result<out T : Any> {
    class Success<out T : Any>(val data: SuggestionResponse?) : Result<T>()
    class Failure(val exception: Exception) : Result<Nothing>()
}