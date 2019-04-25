package com.helpnet.tech.ui.fragments

import com.helpnet.tech.data.network.RequestController
import com.helpnet.tech.ui.activities.BaseActivity

class HomeFragment : GenericListOsFragment() {

    override fun isHomeFragment(): Boolean = true

    override fun fetchOpenServiceOrder() {
        val userInfo = (activity as BaseActivity).getUserInfo()

        RequestController.listOpenOS(userInfo.providerId, callbackResult())
    }
}
