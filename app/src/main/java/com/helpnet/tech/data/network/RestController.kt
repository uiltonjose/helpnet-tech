package com.helpnet.tech.data.network

import android.annotation.SuppressLint
import com.helpnet.tech.HelpNetApplication
import com.helpnet.tech.internal.NoConnectivityException
import com.helpnet.tech.util.AndroidUtil
import com.helpnet.tech.util.SharedPreferenceUtil
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val REQUEST_MAX_AGE = 10 // 10 seconds
private const val REQUEST_MAX_STALE = 60 * 60 * 24 * 5 // 5 days of cache

abstract class RestController {
    companion object {
        @SuppressLint("StaticFieldLeak")
        private val context = HelpNetApplication.application?.applicationContext!!
        private const val cacheSize = (5 * 1024 * 1024).toLong()
        private val myCache = Cache(context.cacheDir, cacheSize)

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
                return OkHttpClient.Builder()
                    .cache(myCache)
                    .readTimeout(45, TimeUnit.SECONDS)
                    .connectTimeout(45, TimeUnit.SECONDS)
                    .addInterceptor(authorizationHeaderInterceptor())
                    .addInterceptor(loggingInterceptor())
                    .addInterceptor(checkConnectivityOrRequestFromCacheInterceptor())
                    .build()
            }

        private fun authorizationHeaderInterceptor(): Interceptor {
            return Interceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", SharedPreferenceUtil.getAccessToken(context))
                    .build()
                return@Interceptor chain.proceed(request)
            }
        }

        private fun loggingInterceptor(): HttpLoggingInterceptor {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            return logging
        }

        private fun checkConnectivityOrRequestFromCacheInterceptor(): Interceptor {
            return Interceptor { chain ->
                var request = chain.request()
                request = if (AndroidUtil.hasConnectivity(context)) {
                    request.newBuilder().header("Cache-Control", "public, max-age=$REQUEST_MAX_AGE").build()
                } else {
                    if (request.method() != "GET") {
                        throw NoConnectivityException()
                    }

                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=$REQUEST_MAX_STALE"
                    ).build()
                }
                return@Interceptor chain.proceed(request)
            }
        }
    }
}
