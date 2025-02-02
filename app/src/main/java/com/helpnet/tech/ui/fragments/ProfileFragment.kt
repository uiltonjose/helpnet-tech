package com.helpnet.tech.ui.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.helpnet.tech.R
import com.helpnet.tech.data.model.Provider
import com.helpnet.tech.data.model.UserInfo
import com.helpnet.tech.data.network.RequestController
import com.helpnet.tech.data.network.response.ProviderResponse
import com.helpnet.tech.ui.activities.BaseActivity
import com.helpnet.tech.util.AlertDialogUtil
import kotlinx.android.synthetic.main.content_profile_data.customerCodeTextView
import kotlinx.android.synthetic.main.content_profile_data.providerCellphoneTextView
import kotlinx.android.synthetic.main.content_profile_data.providerNameTextView
import kotlinx.android.synthetic.main.content_profile_data.providerPhoneTextView
import kotlinx.android.synthetic.main.content_profile_data.userNameTextView
import kotlinx.android.synthetic.main.fragment_profile.providerLogoImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_profile, container, false)
        rootView.findViewById<Button>(R.id.logoutButton).setOnClickListener {
            logout()
        }
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val baseActivity = activity as BaseActivity
        val userInfo = baseActivity.getUserInfo()

        RequestController.getProviderById(userInfo.providerId, object : Callback<ProviderResponse> {
            override fun onFailure(call: Call<ProviderResponse>?, t: Throwable?) {
                Toast.makeText(baseActivity, "Error ao buscar o provedor.", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<ProviderResponse>?, response: Response<ProviderResponse>?) {
                if (response?.isSuccessful!!) {
                    initViews(response.body().provider, userInfo)
                } else {
                    Toast.makeText(baseActivity, "Error ao buscar o provedor.", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun initViews(
        provider: Provider,
        userInfo: UserInfo
    ) {
        val baseActivity = activity as BaseActivity
        baseActivity.downloadResource(provider.getLogo(), providerLogoImageView, false)

        userNameTextView.text = baseActivity.fromHtml(getString(R.string.userNameProfile, userInfo.login))
        providerNameTextView.text = baseActivity.fromHtml(getString(R.string.providerName, provider.providerName))
        customerCodeTextView.text =
            baseActivity.fromHtml(getString(R.string.customerCode, provider.customerCode.toString()))
        providerPhoneTextView.text = baseActivity.fromHtml(getString(R.string.phoneProvider, provider.phone))
        providerCellphoneTextView.text =
            baseActivity.fromHtml(getString(R.string.cellphoneProvider, provider.cellphone))
    }

    private fun logout() {
        AlertDialogUtil.showMessageOKCancel(context,
            getString(R.string.logout),
            getString(R.string.logout_message),
            okListener = DialogInterface.OnClickListener { _, _ ->
                (activity as BaseActivity).doLogout()
            })
    }
}
