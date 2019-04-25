package com.helpnet.tech.data.model

import com.google.gson.annotations.SerializedName

data class Situation(
    @SerializedName("ID") val id: Int,
    @SerializedName("SITUACAO") val type: String
)