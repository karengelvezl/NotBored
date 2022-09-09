package com.example.notbored.ui.service.responseObjects

data class SuggestionResponse(
    var activity: String,
    var type: String,
    var participants: String,
    var price: String,
    var link: String? = "",
    var key: String,
    var accessibility: String,
    var error: String? = ""
)