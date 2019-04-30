package com.helpnet.tech

import android.app.Application
import android.content.Context
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric

open class HelpNetApplication : Application() {

    companion object {
        var application: HelpNetApplication? = null
    }

    lateinit var context: Context

    override fun onCreate() {
        super.onCreate()
        application = this
        Fabric.with(this, Crashlytics())
    }
}