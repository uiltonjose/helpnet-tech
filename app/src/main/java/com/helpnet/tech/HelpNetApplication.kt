package com.helpnet.tech

import android.app.Application
import android.content.Context

open class HelpNetApplication : Application() {

    companion object {
        var application: HelpNetApplication? = null
    }

    lateinit var context: Context

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}