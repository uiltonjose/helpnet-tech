package com.helpnet.tech.data.model

import com.google.gson.annotations.SerializedName

data class OSsimple(
    @SerializedName("Número") val number: Long,
    @SerializedName("Nome") val name: String,
    @SerializedName("Problema") val problem: String,
    @SerializedName("Detalhe") val detail: String,
    @SerializedName("Data Abertura") val dateOpen: String
)
