package com.helpnet.tech.util

import android.content.Context

object SharedPreferenceUtil {

    private const val MyPREFERENCES = "MyPrefs"
    private const val PROVIDER_JSON = "provider_obj"
    private const val ACCESS_TOKEN = "accessToken"

    fun setProviderJson(context: Context, value: String?) {
        val editor = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE).edit()
        editor.putString(PROVIDER_JSON, value)
        editor.apply()
    }

    fun getProviderJson(context: Context): String? {
        val prefs = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE)
        return prefs.getString(PROVIDER_JSON, null)
    }

    fun setAccessToken(context: Context, value: String) {
        val editor = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE).edit()
        editor.putString(ACCESS_TOKEN, value)
        editor.apply()
    }

    fun getAccessToken(context: Context): String {
        val prefs = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE)
        return prefs.getString(ACCESS_TOKEN, "")!!
    }
}