package com.helpnet.tech.data.network

import com.helpnet.tech.data.model.OS
import com.helpnet.tech.data.model.Situation
import retrofit2.Callback

object RequestController {

    private val apiController by lazy {
        RestController.create()
    }

    fun listSituations(callbackResponse: Callback<List<Situation>>) {
        val call = apiController.listSituations()
        call.enqueue(callbackResponse)
    }

    fun listOSBySituation(providerId: Int, situationId: Int, callbackResponse: Callback<List<OS>>) {
        val call = apiController.listOSBySituation(providerId, situationId)
        call.enqueue(callbackResponse)
    }
}
