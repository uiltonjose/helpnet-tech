package com.helpnet.tech.ui.activities

import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.widget.EditText
import android.widget.TextView
import com.helpnet.tech.R
import com.helpnet.tech.data.model.ChangeSituation
import com.helpnet.tech.data.model.Event
import com.helpnet.tech.data.network.RequestController
import com.helpnet.tech.data.network.response.CustomerData
import com.helpnet.tech.data.network.response.OsDetailResponse
import com.helpnet.tech.internal.NoConnectivityException
import com.helpnet.tech.ui.fragments.NetworkScreenFragment
import com.helpnet.tech.util.AlertDialogUtil
import com.helpnet.tech.util.Constants
import com.helpnet.tech.util.EventType
import com.helpnet.tech.util.Situations
import kotlinx.android.synthetic.main.activity_os_detail.osDetailLayout
import kotlinx.android.synthetic.main.activity_os_detail.startFinishServiceCTA
import kotlinx.android.synthetic.main.content_customer_detail.addressNumberTextView
import kotlinx.android.synthetic.main.content_customer_detail.addressTextView
import kotlinx.android.synthetic.main.content_customer_detail.cellphoneTextView
import kotlinx.android.synthetic.main.content_customer_detail.cityTextView
import kotlinx.android.synthetic.main.content_customer_detail.customerIDTextView
import kotlinx.android.synthetic.main.content_customer_detail.customerNameTextView
import kotlinx.android.synthetic.main.content_customer_detail.neighborTextView
import kotlinx.android.synthetic.main.content_customer_detail.phoneTextView
import kotlinx.android.synthetic.main.content_customer_detail.planDetailTextView
import kotlinx.android.synthetic.main.content_customer_detail.referenceDescriptionTextView
import kotlinx.android.synthetic.main.content_os_detail.osDetailDescriptionTextView
import kotlinx.android.synthetic.main.content_os_detail.osNumberTextView
import kotlinx.android.synthetic.main.content_os_detail.problemDescriptionTextView
import org.json.JSONObject
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
            handleFailObtainOsDetail()
            return
        }

        RequestController.getOsDetailByNumber(osNumber, object : Callback<OsDetailResponse> {
            override fun onFailure(call: Call<OsDetailResponse>?, t: Throwable?) {
                handleFailObtainOsDetail()
            }

            override fun onResponse(call: Call<OsDetailResponse>?, response: Response<OsDetailResponse>?) {
                if (response?.isSuccessful!!) {
                    response.body()?.also {
                        setupViews(it.customerData)
                    } ?: handleFailObtainOsDetail()
                } else {
                    handleFailObtainOsDetail()
                }
            }
        })
    }

    private fun handleFailObtainOsDetail() {
        Handler().post {
            AlertDialogUtil.showMessageOK(this@OpenServiceDetailActivity,
                title = getString(R.string.attention_title),
                message = getString(R.string.fail_obtain_os_detail),
                okListener = DialogInterface.OnClickListener { _, _ ->
                    finish()
                })
        }
    }

    private fun setupViews(customerData: CustomerData) {
        // Os Data

        addValueStyled(osNumberTextView, getString(R.string.osNumber, customerData.osNumber.toString()))
        addValueStyled(problemDescriptionTextView, getString(R.string.problemData, customerData.problem))
        addValueStyled(osDetailDescriptionTextView, getString(R.string.osDetailData, customerData.detailOS))

        // Customer Data
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

        // Address
        addValueStyled(addressTextView, getString(R.string.addressCustomer, customerData.address))
        addValueStyled(addressNumberTextView, getString(R.string.addressNumber, customerData.addressNumber))
        addValueStyled(neighborTextView, getString(R.string.addressNeighbor, customerData.neighbor))
        addValueStyled(cityTextView, getString(R.string.addressCity, customerData.city))

        val isWIPStatus = intent?.extras?.getBoolean(Constants.OS_WIP_PARAM)!!
        if (isWIPStatus) {
            startFinishServiceCTA.text = getString(R.string.finish_working_cta)
            startFinishServiceCTA.setOnClickListener {
                finishOSAttend(customerData)
            }
        } else {
            startFinishServiceCTA.setOnClickListener {
                changeToWIP(customerData)
            }
        }
    }

    private fun changeToWIP(customerData: CustomerData) {
        AlertDialogUtil.showMessageOKCancel(this@OpenServiceDetailActivity,
            "OS: ${customerData.osNumber}",
            "Tem certeza que deseja iniciar o atendimento ao cliente ${customerData.customerName}?",
            okListener = DialogInterface.OnClickListener { _, _ ->

                val event = Event(
                    getUserInfo().id,
                    EventType.PUT_IN_PROGRESS.value,
                    ""
                )

                val changeSituation = ChangeSituation(
                    customerData.osNumber,
                    Situations.WORK_IN_PROGRESS.value,
                    null,
                    getUserInfo().id,
                    event
                )
                changeSituation(changeSituation)
            })
    }

    private fun finishOSAttend(customerData: CustomerData) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_finishing_os_layout, null)
        val problemResolution = dialogView.findViewById<EditText>(R.id.etProblemResolution)
        val messageToCustomer = dialogView.findViewById<EditText>(R.id.etMessageToCustomer)

        AlertDialogUtil.showCustomDialog(this@OpenServiceDetailActivity,
            "Finalizar OS ${customerData.osNumber}?",
            dialogView,
            okListener = DialogInterface.OnClickListener { _, _ ->

                val event = Event(
                    getUserInfo().id,
                    EventType.CLOSE_OS.value,
                    problemResolution.text.toString()
                )

                val changeSituation = ChangeSituation(
                    customerData.osNumber,
                    Situations.CLOSE.value,
                    messageToCustomer.text.toString(),
                    getUserInfo().id,
                    event
                )
                changeSituation(changeSituation)
            })
    }

    private fun changeSituation(situation: ChangeSituation) {
        RequestController.changeSituation(situation, object : Callback<JSONObject> {
            override fun onFailure(call: Call<JSONObject>?, t: Throwable?) {
                if (t is NoConnectivityException) {
                    val ft = supportFragmentManager.beginTransaction()
                    ft.add(android.R.id.content, NetworkScreenFragment(), NetworkScreenFragment.TAG)
                    ft.commit()
                } else {
                    errorMessage()
                }
            }

            override fun onResponse(call: Call<JSONObject>?, response: Response<JSONObject>?) {
                if (response?.isSuccessful!!) {
                    val complement =
                        if (situation.situationId == Situations.WORK_IN_PROGRESS.value) "iniciado" else "finalizado"

                    AlertDialogUtil.showMessageOK(this@OpenServiceDetailActivity,
                        message = "Atendimento $complement com sucesso.",
                        okListener = DialogInterface.OnClickListener { _, _ ->
                            finish()
                        })
                } else {
                    errorMessage()
                }
            }

            private fun errorMessage() {
                val complement =
                    if (situation.situationId == Situations.WORK_IN_PROGRESS.value) "iniciar" else "finalizar"
                showMessage(osDetailLayout, "Não foi possível $complement o atendimento a esta OS.")
            }
        })
    }

    private fun addValueStyled(textView: TextView, value: String) {
        textView.text = fromHtml(value)
    }
}