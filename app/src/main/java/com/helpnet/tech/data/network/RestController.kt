package com.helpnet.tech.data.network

import com.helpnet.tech.HelpNetApplication
import com.helpnet.tech.util.SharedPreferenceUtil
import okhttp3.Interceptor
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

                val authHeader = Interceptor { chain ->

                    val request = chain.request()
                        .newBuilder()
                        .addHeader("Authorization", SharedPreferenceUtil.getAccessToken(HelpNetApplication.application?.applicationContext!!))
                        .build()

                    return@Interceptor chain.proceed(request)
                }

                return OkHttpClient.Builder()
                    .readTimeout(45, TimeUnit.SECONDS)
                    .connectTimeout(45, TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    .addInterceptor(authHeader)
                    .build()
            }
    }
}
