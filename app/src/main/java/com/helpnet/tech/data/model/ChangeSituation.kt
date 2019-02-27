package com.helpnet.tech.data.model

import com.google.gson.annotations.SerializedName

data class ChangeSituation(
    @SerializedName("osId") val osId: Int?,
    @SerializedName("situationId") val situationId: Int?,
    @SerializedName("event") val event: Event?
)