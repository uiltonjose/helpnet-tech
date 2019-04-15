package com.helpnet.tech.ui.activities

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.helpnet.tech.data.model.response.OsDetailResponse
import com.helpnet.tech.data.network.RequestController
import com.helpnet.tech.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OpenServiceDetail : BaseActivity() {
    private var resultTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        resultTextView = TextView(this)
        val layout = LinearLayout(this)
        layout.addView(resultTextView)
        setContentView(layout)

        fetchOsDetail()
    }

    private fun fetchOsDetail() {
        val osNumber = intent?.extras?.getLong(Constants.OS_NUMBER_PARAM) ?: 0
        RequestController.getOsDetailByNumber(osNumber, object : Callback<OsDetailResponse> {
            override fun onFailure(call: Call<OsDetailResponse>?, t: Throwable?) {
                Toast.makeText(this@OpenServiceDetail, "Shiiiiteee", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<OsDetailResponse>?, response: Response<OsDetailResponse>?) {
                if (response?.isSuccessful!! && response.body() != null) {
                    resultTextView?.text = response.body().customerData.toString()
                }
            }

        })
    }
}