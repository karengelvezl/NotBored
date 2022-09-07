package com.example.notbored.ui.service.responseObjects

import java.io.Serializable

data class SuggestionResponse(var activity: String,
                              var type: String,
                              var participants: Int,
                              var price: Int,
                              var link: String,
                              var key: String,
                              var accessibility: Int)
