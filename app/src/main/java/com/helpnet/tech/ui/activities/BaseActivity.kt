package com.helpnet.tech.ui.activities

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.PorterDuff
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.*
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import com.helpnet.tech.R

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    private var requestManager: RequestManager? = null
    private var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    private fun init() {
        requestManager = Glide.with(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        requestManager?.onDestroy()
    }

    fun initToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    protected fun initToolbar(title: Int, hasBack: Boolean) {
        initToolbar(title, R.color.white, hasBack)
    }

    private fun initToolbar(title: Int, color: Int, hasBack: Boolean) {

        if (toolbar == null) {
            initToolbar()
        }

        setTitleActionBar(title)

        toolbar!!.setTitleTextColor(ContextCompat.getColor(this, color))
        if (hasBack) {
            toolbar!!.setNavigationIcon(R.drawable.ic_arrow_back)
            toolbar!!.setNavigationOnClickListener { finish() }
        }
        if (toolbar!!.navigationIcon != null) {
            toolbar!!.navigationIcon!!.setColorFilter(ContextCompat.getColor(this, color), PorterDuff.Mode.MULTIPLY)
        }
    }

    private fun setTitleActionBar(title: Int) {
        var titleStr = ""
        if (title != -1) {
            titleStr = getString(title)
        }
        if (supportActionBar != null) {
            supportActionBar!!.title = titleStr
        }
    }

    protected fun setTitleActionBar(title: String) {
        var titleStr: String? = title
        if (titleStr == null) {
            titleStr = ""
        }
        if (supportActionBar != null) {
            supportActionBar!!.title = titleStr
        }
    }

    open fun downloadResource(url: String, imageView: ImageView?) {
        downloadResource(url, imageView, true)
    }

    open fun downloadResource(url: String, imageView: ImageView?, centerCrop: Boolean) {

        if (requestManager != null) {
            if (centerCrop) {
                requestManager!!.load(url)
                    .asBitmap().centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .skipMemoryCache(true)
                    .into(getBitmapTarget(imageView))
            } else {
                requestManager!!.load(url)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(imageView)
            }
        }
    }

    private fun getBitmapTarget(imageView: ImageView?): BitmapImageViewTarget {
        return object : BitmapImageViewTarget(imageView) {
            override fun setResource(resource: Bitmap) {
                val circularBitmapDrawable = RoundedBitmapDrawableFactory.create(resources, resource)
                circularBitmapDrawable.isCircular = true
                imageView?.setImageDrawable(circularBitmapDrawable)
            }
        }
    }

    protected fun setErrorLayout(ti: TextInputLayout, validationMsg: Int) {
        ti.isErrorEnabled = true
        ti.error = getString(validationMsg)
    }

    fun editTextWatcherListener(inputLayout: TextInputLayout): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun afterTextChanged(editable: Editable) {
                validEditText(editable, inputLayout)
            }
        }
    }

    protected fun validEditText(s: Editable, inputLayout: TextInputLayout) {
        if (!TextUtils.isEmpty(s)) {
            inputLayout.error = null
        }
    }

    protected fun showMessage(view: View?, message: Int) {
        view?.also {
            Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
        }
    }

    fun fromHtml(html: String): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(html)
        }
    }
}