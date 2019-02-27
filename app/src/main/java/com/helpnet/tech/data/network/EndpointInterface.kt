package com.helpnet.tech.data.network

import com.helpnet.tech.data.model.OS
import com.helpnet.tech.data.model.Situation
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

const val CONTENT_TYPE_JSON = "Content-Type:application/json"

interface EndpointInterface {

    @Headers(CONTENT_TYPE_JSON)
    @GET("os/listSituations")
    fun listSituations(): Call<List<Situation>>

    @Headers(CONTENT_TYPE_JSON)
    @GET("os/listBySituation")
    fun listOSBySituation(
        @Query("providerId") providerId: Int,
        @Query("situationId") situationId: Int
    ): Call<List<OS>>

//    @Headers(CONTENT_TYPE_JSON)
//    @POST("os/register")
//    fun registerOS(@Body bodyRequest: OS): Call<String>

}