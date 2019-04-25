package com.helpnet.tech.data.network.response

import com.google.gson.annotations.SerializedName
import com.helpnet.tech.data.model.UserInfo

data class GetUserInfoResponse(
    @SerializedName("userInfo") val userInfo: UserInfo
)