package com.helpnet.tech.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import com.google.gson.Gson
import com.helpnet.tech.R
import com.helpnet.tech.data.network.response.GetUserInfoResponse
import com.helpnet.tech.data.network.RequestController
import com.helpnet.tech.util.AndroidUtil
import com.helpnet.tech.util.SharedPreferenceUtil
import kotlinx.android.synthetic.main.activity_login.etUserEmail
import kotlinx.android.synthetic.main.activity_login.etUserPassword
import kotlinx.android.synthetic.main.activity_login.loginButton
import kotlinx.android.synthetic.main.activity_login.progressIndicator
import kotlinx.android.synthetic.main.activity_login.tilUserEmail
import kotlinx.android.synthetic.main.activity_login.tilUserPassword
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()
    }

    private fun initViews() {
        editTextWatcherListener(tilUserEmail)
        etUserEmail.addTextChangedListener(editTextWatcherListener(tilUserEmail))
        editTextWatcherListener(tilUserPassword)
        etUserPassword.addTextChangedListener(editTextWatcherListener(tilUserPassword))
        etUserPassword.setOnEditorActionListener { _, actionID, _ ->
            var handled = false
            if (actionID == EditorInfo.IME_ACTION_DONE) {
                dispatchLogin()
                handled = true
            }
            return@setOnEditorActionListener handled
        }

        loginButton.setOnClickListener {
            dispatchLogin()
        }
    }

    private fun dispatchLogin() {
        AndroidUtil.hideSoftKeyboard(etUserPassword, this)
        progressIndicator()

        if (validateInputLogin()) {
            auth.signInWithEmailAndPassword(etUserEmail.text.toString(), etUserPassword.text.toString())
                .addOnCompleteListener(this@LoginActivity) { task ->
                    if (task.isSuccessful) {
                        fetchUserInfo()
                    } else {
                        showMessage(etUserEmail, R.string.invalid_login)
                        progressIndicator(false)
                    }
                }
        } else {
            progressIndicator(false)
        }
    }

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            goToHomeScreen()
        }
    }

    private fun goToHomeScreen() {
        Intent(this@LoginActivity, MainActivity::class.java).apply {
            startActivity(this)
            finish()
        }
    }

    private fun validateInputLogin(): Boolean {
        var isValid = true

        if (etUserEmail.text?.trim()?.isEmpty()!! && etUserPassword.text?.trim()?.isEmpty()!!) {
            isValid = false
            setErrorLayout(tilUserEmail, R.string.email_required)
            setErrorLayout(tilUserPassword, R.string.password_required)
        } else if (etUserEmail.text?.trim()?.isEmpty()!!) {
            isValid = false
            setErrorLayout(tilUserEmail, R.string.email_required)
        } else if (etUserPassword.text?.trim()?.isEmpty()!!) {
            isValid = false
            setErrorLayout(tilUserPassword, R.string.password_required)
        }

        return isValid
    }

    private fun progressIndicator(showIndicator: Boolean = true) {
        if (showIndicator) {
            progressIndicator.visibility = View.VISIBLE
            loginButton.visibility = View.GONE
        } else {
            progressIndicator.visibility = View.GONE
            loginButton.visibility = View.VISIBLE
        }
    }

    private fun fetchUserInfo() {
        RequestController.getUserInfo(etUserEmail.text.toString(), object : Callback<GetUserInfoResponse> {
            override fun onFailure(call: Call<GetUserInfoResponse>?, t: Throwable?) {
                showMessage(etUserEmail, getString(R.string.fail_obtain_user_info))
            }

            override fun onResponse(call: Call<GetUserInfoResponse>?, response: Response<GetUserInfoResponse>?) {
                if (response?.isSuccessful!!) {
                    response.body().apply {
                        SharedPreferenceUtil.setProviderJson(this@LoginActivity, Gson().toJson(userInfo))
                        goToHomeScreen()
                    }
                } else {
                    showMessage(etUserEmail, getString(R.string.fail_obtain_user_info))
                }
            }
        })
    }
}