package com.helpnet.tech.ui.activities

import android.content.Intent
import android.os.Bundle
import com.helpnet.tech.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener {
            Intent(this@LoginActivity, MainActivity::class.java).apply {
                startActivity(this)
            }
        }
    }
}