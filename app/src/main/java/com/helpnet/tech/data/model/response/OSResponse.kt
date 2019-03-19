package com.helpnet.tech.data.model.response

import com.google.gson.annotations.SerializedName
import com.helpnet.tech.data.model.OSsimple

data class OSResponse(
    @SerializedName("message") val osList: List<OSsimple>
)