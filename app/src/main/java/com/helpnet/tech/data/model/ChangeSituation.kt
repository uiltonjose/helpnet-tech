package com.helpnet.tech.data.model

import com.google.gson.annotations.SerializedName

data class ChangeSituation(
    @SerializedName("osNumber") val osNumber: Long?,
    @SerializedName("situationId") val situationId: Int?,
    @SerializedName("messageToCustomer") val messageToCustomer: String? = null,
    @SerializedName("userId") val userId: Int?,
    @SerializedName("event") val event: Event?
)