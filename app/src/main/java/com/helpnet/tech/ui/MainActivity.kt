package com.helpnet.tech.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.helpnet.tech.R
import com.helpnet.tech.data.model.OS
import com.helpnet.tech.data.network.RequestController
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                RequestController.listOSBySituation(3, 1, object : Callback<List<OS>> {
                    override fun onFailure(call: Call<List<OS>>?, t: Throwable?) {
                        Toast.makeText(this@MainActivity, "Shitt", Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<List<OS>>?, response: Response<List<OS>>?) {
                        if (response?.isSuccessful!! && response.body() != null) {
                            message.text = response.body().toString()
                        }
                    }

                })

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
