package com.helpnet.tech.data.network

import com.helpnet.tech.data.model.ChangeSituation
import com.helpnet.tech.data.model.Situation
import com.helpnet.tech.data.network.response.GetUserInfoResponse
import com.helpnet.tech.data.network.response.OSResponse
import com.helpnet.tech.data.network.response.OsDetailResponse
import com.helpnet.tech.data.network.response.ProviderResponse
import org.json.JSONObject
import retrofit2.Callback

object RequestController {

    private val apiController by lazy {
        RestController.create()
    }

    fun listSituations(callbackResponse: Callback<List<Situation>>) {
        val call = apiController.listSituations()
        call.enqueue(callbackResponse)
    }

    fun listOpenOS(providerId: Int, callbackResponse: Callback<OSResponse>) {
        val call = apiController.listOpenOS(providerId)
        call.enqueue(callbackResponse)
    }

    fun listInProgressOS(providerId: Int, callbackResponse: Callback<OSResponse>) {
        val call = apiController.listProgressOS(providerId)
        call.enqueue(callbackResponse)
    }

    fun getOsDetailByNumber(osNumber: Long, callbackResponse: Callback<OsDetailResponse>) {
        val call = apiController.getOsDetailByNumber(osNumber)
        call.enqueue(callbackResponse)
    }

    fun getUserInfo(email: String, callbackResponse: Callback<GetUserInfoResponse>) {
        val call = apiController.getUserInfo(email)
        call.enqueue(callbackResponse)
    }

    fun changeSituation(changeSituation: ChangeSituation, callbackResponse: Callback<JSONObject>) {
        val call = apiController.changeSituation(changeSituation)
        call.enqueue(callbackResponse)
    }

    fun getProviderById(providerId: Int, callbackResponse: Callback<ProviderResponse>) {
        val call = apiController.getProviderById(providerId)
        call.enqueue(callbackResponse)
    }
}
