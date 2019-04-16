package com.helpnet.tech.data.model.response

import com.google.gson.annotations.SerializedName

data class OsDetailResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("data") val customerData: CustomerData
)

data class CustomerData(
    @SerializedName("bairro") val neighbor: String,
    @SerializedName("celular") val cellphone: String,
    @SerializedName("cep") val cep: String,
    @SerializedName("cidade") val city: String,
    @SerializedName("cpf_cnpj") val cpfCnpj: String,
    @SerializedName("dataCadastroProvedor") val providerRegisterDate: String,
    @SerializedName("detalhesOS") val detailOS: String,
    @SerializedName("emailEnvioOS") val emailEnvioOS: String,
    @SerializedName("endereco") val address: String,
    @SerializedName("estado") val state: String,
    @SerializedName("event") val events: List<Event>,
    @SerializedName("fone") val phone: String,
    @SerializedName("login") val login: String,
    @SerializedName("nomeCliente") val customerName: String,
    @SerializedName("nome_res") val referenceDescription: String,
    @SerializedName("numero") val addressNumber: String,
    @SerializedName("numeroOS") val osNumber: Long,
    @SerializedName("plano") val plan: String,
    @SerializedName("problema") val problem: String
)

data class Event(
    @SerializedName("Data hora do evento") val eventDate: String,
    @SerializedName("Descrição do evento") val description: String,
    @SerializedName("Observação") val observation: String,
    @SerializedName("Responsável pela OS") val osResponsible: String,
    @SerializedName("Usuário do evento") val userEvent: String
)