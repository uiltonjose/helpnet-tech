package com.helpnet.tech.data.network

import com.helpnet.tech.data.model.ChangeSituation
import com.helpnet.tech.data.network.response.GetUserInfoResponse
import com.helpnet.tech.data.network.response.OSResponse
import com.helpnet.tech.data.network.response.OsDetailResponse
import com.helpnet.tech.data.network.response.ProviderResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

const val CONTENT_TYPE_JSON = "Content-Type:application/json"

interface EndpointInterface {

    @Headers(CONTENT_TYPE_JSON)
    @GET("os/listOsByProviderIdAndSituationOpened")
    fun listOpenOS(@Query("providerId") providerId: Int): Call<OSResponse>

    @Headers(CONTENT_TYPE_JSON)
    @GET("os/listOsByProviderIdAndInProgress")
    fun listProgressOS(@Query("providerId") providerId: Int): Call<OSResponse>

    @Headers(CONTENT_TYPE_JSON)
    @GET("os/getOsByNumber")
    fun getOsDetailByNumber(@Query("numberOS") numberOS: Long): Call<OsDetailResponse>

    @Headers(CONTENT_TYPE_JSON)
    @GET("user/info")
    fun getUserInfo(@Query("userLogin") numberOS: String): Call<GetUserInfoResponse>

    @Headers(CONTENT_TYPE_JSON)
    @POST("os/changeSituation")
    fun changeSituation(@Body bodyRequest: ChangeSituation): Call<JSONObject>

    @Headers(CONTENT_TYPE_JSON)
    @GET("provider")
    fun getProviderById(@Query("providerId") providerId: Int): Call<ProviderResponse>
}