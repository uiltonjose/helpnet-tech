package com.helpnet.tech.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.helpnet.tech.R
import com.helpnet.tech.data.model.OSsimple
import com.helpnet.tech.data.network.response.OSResponse
import com.helpnet.tech.data.network.RequestController
import com.helpnet.tech.ui.activities.BaseActivity
import com.helpnet.tech.ui.adapters.ServiceOrderAdapter
import kotlinx.android.synthetic.main.fragment_home.list_os
import kotlinx.android.synthetic.main.fragment_home.tv_empty_list
import kotlinx.android.synthetic.main.loading_layout.layout_loading
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_home, container, false)
        return rootView
    }

    override fun onResume() {
        super.onResume()
        fetchOpenServiceOrder()
    }

    private fun fetchOpenServiceOrder() {
        val userInfo = (activity as BaseActivity).getUserInfo()

        RequestController.listOpenOS(userInfo.providerId, object : Callback<OSResponse> {
            override fun onFailure(call: Call<OSResponse>?, t: Throwable?) {
                val messageError = "Something went wrong... ${t?.message}"
                showPageError(messageError)
            }

            override fun onResponse(call: Call<OSResponse>?, response: Response<OSResponse>?) {
                if (response?.isSuccessful!! && response.body() != null) {
                    val body = response.body()
                    body?.osList?.also {
                        setupViews(it)
                    } ?: Toast.makeText(this@HomeFragment.activity, "Shiiiiii", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@HomeFragment.activity, "Shiiiiii", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun setupViews(osList: List<OSsimple>) {
        layout_loading.visibility = GONE

        if (osList.isNotEmpty()) {
            tv_empty_list.visibility = GONE

            list_os.setHasFixedSize(true)
            list_os.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

            osList.sortedWith(Comparator { o1, o2 -> o2?.dateOpen!!.compareTo(o1?.dateOpen!!) }).let {
                val serviceOrderAdapter = ServiceOrderAdapter(it, false)
                list_os.adapter = serviceOrderAdapter
                list_os.adapter?.notifyDataSetChanged()
            }
        } else {
            list_os.visibility = GONE
            tv_empty_list.visibility = VISIBLE
        }
    }

    private fun showPageError(message: String) {
        layout_loading.visibility = GONE
        tv_empty_list.visibility = VISIBLE
        tv_empty_list.text = message
    }
}
