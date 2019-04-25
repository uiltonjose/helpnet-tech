package com.helpnet.tech.ui.fragments

import com.helpnet.tech.data.network.RequestController
import com.helpnet.tech.ui.activities.BaseActivity

class InProgressFragment : GenericListOsFragment() {

    override fun isHomeFragment(): Boolean = false

    override fun fetchOpenServiceOrder() {
        val userInfo = (activity as BaseActivity).getUserInfo()

        RequestController.listInProgressOS(userInfo.providerId, callbackResult())
    }
}
