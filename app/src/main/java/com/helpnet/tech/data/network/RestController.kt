package com.helpnet.tech.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class RestController {
    companion object {

        fun create(): EndpointInterface {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://helpnetws.herokuapp.com/api/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(EndpointInterface::class.java)
        }

        private val client: OkHttpClient
            get() {

                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY

                return OkHttpClient.Builder()
                    .readTimeout(45, TimeUnit.SECONDS)
                    .connectTimeout(45, TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    .build()
            }
    }

}
