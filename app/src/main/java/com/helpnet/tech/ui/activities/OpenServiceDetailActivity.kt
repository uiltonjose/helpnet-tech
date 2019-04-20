package com.helpnet.tech.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.helpnet.tech.R
import com.helpnet.tech.data.model.response.CustomerData
import com.helpnet.tech.data.model.response.OsDetailResponse
import com.helpnet.tech.data.network.RequestController
import com.helpnet.tech.util.Constants
import kotlinx.android.synthetic.main.activity_os_detail.*
import kotlinx.android.synthetic.main.content_customer_detail.*
import kotlinx.android.synthetic.main.content_os_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OpenServiceDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_os_detail)
        initToolbar(R.string.os_detail_screen_title, true)
    }

    override fun onStart() {
        super.onStart()
        fetchOsDetail()
    }

    private fun fetchOsDetail() {
        val osNumber: Long = intent?.extras?.getLong(Constants.OS_NUMBER_PARAM) ?: 0L
        if (osNumber == 0L) {
            //TODO handle this better
            Toast.makeText(this, "Something went wrong...", Toast.LENGTH_LONG).show()
            finish()
        }

        RequestController.getOsDetailByNumber(osNumber, object : Callback<OsDetailResponse> {
            override fun onFailure(call: Call<OsDetailResponse>?, t: Throwable?) {
                //TODO handle this better
                Toast.makeText(this@OpenServiceDetailActivity, "Shiiiiteee", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<OsDetailResponse>?, response: Response<OsDetailResponse>?) {
                if (response?.isSuccessful!!) {
                    response.body()?.also {
                        setupViews(it.customerData)
                    } ?: Toast.makeText(
                        this@OpenServiceDetailActivity,
                        "Something went wrong...",
                        Toast.LENGTH_LONG
                        //TODO handle this better
                    ).show()
                } else {
                    //TODO handle this better
                    Toast.makeText(this@OpenServiceDetailActivity, "Something went wrong...", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun setupViews(customerData: CustomerData) {
        //Os Data

        addValueStyled(osNumberTextView, getString(R.string.osNumber, customerData.osNumber.toString()))
        addValueStyled(problemDescriptionTextView, getString(R.string.problemData, customerData.problem))
        addValueStyled(osDetailDescriptionTextView, getString(R.string.osDetailData, customerData.detailOS))

        //Customer Data
        addValueStyled(customerIDTextView, getString(R.string.customerID, customerData.cpfCnpj))
        addValueStyled(customerNameTextView, getString(R.string.customerName, customerData.customerName))
        addValueStyled(
            referenceDescriptionTextView,
            getString(R.string.referenceDescription, customerData.referenceDescription)
        )
        addValueStyled(phoneTextView, getString(R.string.phoneCustomer, customerData.phone))
        addValueStyled(cellphoneTextView, getString(R.string.cellphoneCustomer, customerData.cellphone))
        addValueStyled(planDetailTextView, getString(R.string.plainDetail, customerData.plan))
        addValueStyled(planDetailTextView, getString(R.string.plainDetail, customerData.plan))

        //Address
        addValueStyled(addressTextView, getString(R.string.addressCustomer, customerData.address))
        addValueStyled(addressNumberTextView, getString(R.string.addressNumber, customerData.addressNumber))
        addValueStyled(neighborTextView, getString(R.string.addressNeighbor, customerData.neighbor))
        addValueStyled(cityTextView, getString(R.string.addressCity, customerData.city))

        val isWIPStatus = intent?.extras?.getBoolean(Constants.OS_WIP_PARAM)!!
        if (isWIPStatus) {
            startFinishServiceCTA.text = getString(R.string.finish_working_cta)
            //startFinishServiceCTA.background = ContextCompat.getDrawable(this, R.drawable.secondary_button_rounded_shape)
            startFinishServiceCTA.setOnClickListener {
                //TODO
                Toast.makeText(this, "Ainda não tá pronto, avexado....", Toast.LENGTH_LONG).show()
            }
        } else {
            startFinishServiceCTA.setOnClickListener {
                //TODO
                Toast.makeText(this, "Ainda não tá pronto, avexado....", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun addValueStyled(textView: TextView, value: String) {
        textView.text = fromHtml(value)
    }
}