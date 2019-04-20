package com.helpnet.tech.util

import android.content.Context

object SharedPreferenceUtil {

    private const val MyPREFERENCES = "MyPrefs"
    private const val PROVIDER_JSON = "provider_obj"

    fun setProviderJson(context: Context, value: String?) {
        val editor = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE).edit()
        editor.putString(PROVIDER_JSON, value)
        editor.apply()
    }

    fun getProviderJson(context: Context): String? {
        val prefs = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE)
        return prefs.getString(PROVIDER_JSON, null)
    }
}