package com.helpnet.tech.data.model

import com.google.gson.annotations.SerializedName

data class OS(
    @SerializedName("ID") val id: Int,
    @SerializedName("NUMERO") val number: Long,
    @SerializedName("DATA_ABERTURA") val dateOpen: String,
    @SerializedName("CLIENTE_ID") val customerId: Int,
    @SerializedName("PROBLEMA_ID") val problemId: Int,
    @SerializedName("DETALHES") val details: String?,
    @SerializedName("OBSERVACAO") val moreDetail: String?,
    @SerializedName("USUARIO_ID") val userId: Int?,
    @SerializedName("SITUACAO_ID") val situationId: Int?,
    @SerializedName("PROVEDOR_ID") val providerId: Int?
)
