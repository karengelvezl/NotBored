package com.example.notbored.ui.service.responseObjects

data class SuggestionResponse(
    var activity: String,
    var type: String,
    var participants: Int,
    var price: Int,
    var link: String,
    var key: String,
    var accessibility: Int)
