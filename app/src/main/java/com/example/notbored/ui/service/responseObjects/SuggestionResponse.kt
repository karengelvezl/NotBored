package com.example.notbored.ui.service.responseObjects

// this is a container for the data sent from the API
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