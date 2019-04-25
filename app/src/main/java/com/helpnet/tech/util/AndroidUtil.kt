package com.helpnet.tech.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.net.ConnectivityManager
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager

object AndroidUtil {

    fun hasConnectivity(context: Context?): Boolean {
        return if (context != null) {
            val connectivityManager = context
                .applicationContext.getSystemService(
                Context.CONNECTIVITY_SERVICE
            ) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            (networkInfo != null && networkInfo.isConnected)
        } else {
            false
        }
    }

    fun hideSoftKeyboard(view: View?, context: Context) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    fun convertDP(context: Context, dp: Float): Int {
        return convertToPX(dp, TypedValue.COMPLEX_UNIT_DIP, context)
    }

    private fun convertToPX(value: Float, scale: Int, context: Context): Int {
        return TypedValue.applyDimension(
            scale, value, context
                .resources.displayMetrics
        ).toInt()
    }

    fun createRoundedCornerDrawable(context: Context, radius: Float, color: Int, colorStroke: Int): Drawable {
        val gd = GradientDrawable()
        gd.setColor(color)
        gd.cornerRadius = convertDP(context, radius).toFloat()
        gd.setStroke(2, colorStroke)
        return gd
    }
}
