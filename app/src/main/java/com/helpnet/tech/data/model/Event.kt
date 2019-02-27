package com.helpnet.tech.data.model

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("userId") val userId: Int?,
    @SerializedName("eventTypeID") val eventTypeID: Int?,
    @SerializedName("description") val description: String?
)
