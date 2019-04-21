package com.helpnet.tech.data.model.response

import com.google.gson.annotations.SerializedName
import com.helpnet.tech.data.model.Provider

data class ProviderResponse(
    @SerializedName("data") val provider: Provider
)