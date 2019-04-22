package com.helpnet.tech.ui.activities

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.text.*
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.helpnet.tech.R
import com.helpnet.tech.data.model.UserInfo
import com.helpnet.tech.util.SharedPreferenceUtil

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    private var requestManager: RequestManager? = null
    private var toolbar: Toolbar? = null
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    override fun onStart() {
        super.onStart()
        auth = FirebaseAuth.getInstance()
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

    open fun downloadResource(url: String, imageView: ImageView?, centerCrop: Boolean = true) {
        requestManager?.also {
            if (centerCrop) {
                it.load(url)
                    .asBitmap().centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .skipMemoryCache(true)
                    .placeholder(getImageProgress())
                    .into(getBitmapTarget(imageView))
            } else {
                it.load(url)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .placeholder(getImageProgress())
                    .into(imageView)
            }
        }
    }

    private fun getImageProgress(): CircularProgressDrawable {
        val circularProgressDrawable = CircularProgressDrawable(this)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        return circularProgressDrawable
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
        showMessage(view, getString(message))
    }

    protected fun showMessage(view: View?, message: String) {
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

    fun getUserInfo(): UserInfo {
        val providerJson = SharedPreferenceUtil.getProviderJson(this)
        return Gson().fromJson(providerJson, UserInfo::class.java)
    }
}