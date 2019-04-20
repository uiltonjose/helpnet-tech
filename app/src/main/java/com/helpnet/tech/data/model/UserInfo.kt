package com.helpnet.tech.data.model

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("id") val id: Int,
    @SerializedName("login") val login: String,
    @SerializedName("perfil") val profile: String,
    @SerializedName("provedor_id") val providerId: Int,
    @SerializedName("status") val status: String
)