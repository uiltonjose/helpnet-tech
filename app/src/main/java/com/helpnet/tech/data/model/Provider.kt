package com.helpnet.tech.data.model

import com.google.gson.annotations.SerializedName

data class Provider(
    @SerializedName("ID") val id: Int,
    @SerializedName("NOME") val providerName: String,
    @SerializedName("LOGO") val logo: String,
    @SerializedName("CODIGO") val codeBO: Int,
    @SerializedName("CODIGO_CLIENTE") val customerCode: Int,
    @SerializedName("EMAIL") val email: String,
    @SerializedName("SITUACAO") val status: String,
    @SerializedName("CELULAR_CONTATO") val cellphone: String,
    @SerializedName("TELEFONE_CONTATO") val phone: String
) {
    fun isActive(): Boolean {
        return status == "Ativo"
    }
}