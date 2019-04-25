package com.helpnet.tech.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.helpnet.tech.R
import com.helpnet.tech.data.model.OSsimple
import com.helpnet.tech.data.network.response.OSResponse
import com.helpnet.tech.ui.adapters.ServiceOrderAdapter
import kotlinx.android.synthetic.main.fragment_list_generic_os.list_os
import kotlinx.android.synthetic.main.fragment_list_generic_os.thumbsImageView
import kotlinx.android.synthetic.main.fragment_list_generic_os.tv_empty_layout
import kotlinx.android.synthetic.main.fragment_list_generic_os.tv_empty_list
import kotlinx.android.synthetic.main.loading_layout.layout_loading
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class GenericListOsFragment : Fragment() {

    lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_list_generic_os, container, false)
        return rootView
    }

    override fun onResume() {
        super.onResume()
        fetchOpenServiceOrder()
    }

    abstract fun fetchOpenServiceOrder()

    abstract fun isHomeFragment(): Boolean

    fun setupViews(osList: List<OSsimple>) {
        layout_loading.visibility = GONE

        if (osList.isNotEmpty()) {
            tv_empty_layout.visibility = GONE

            list_os.setHasFixedSize(true)
            list_os.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

            osList.sortedWith(Comparator { o1, o2 -> o2?.dateOpen!!.compareTo(o1?.dateOpen!!) }).let {
                val serviceOrderAdapter = ServiceOrderAdapter(it, isHomeFragment().not())
                list_os.adapter = serviceOrderAdapter
                list_os.adapter?.notifyDataSetChanged()
            }
        } else {
            list_os.visibility = GONE
            tv_empty_layout.visibility = VISIBLE
        }
    }

    fun showPageError(message: String) {
        layout_loading.visibility = GONE
        tv_empty_layout.visibility = VISIBLE
        thumbsImageView.visibility = GONE
        tv_empty_list.text = message
    }

    fun callbackResult(): Callback<OSResponse> {
        return object : Callback<OSResponse> {
            override fun onFailure(call: Call<OSResponse>?, t: Throwable?) {
                Log.e(HomeFragment::class.java.simpleName, "Something went wrong... ${t?.message}")
                getString(R.string.fail_obtain_os_list)
            }

            override fun onResponse(call: Call<OSResponse>?, response: Response<OSResponse>?) {
                if (response?.isSuccessful!! && response.body() != null) {
                    val body = response.body()
                    body?.osList?.also {
                        setupViews(it)
                    } ?: showPageError(getString(R.string.fail_obtain_os_list))
                } else {
                    showPageError(getString(R.string.fail_obtain_os_list))
                }
            }
        }
    }

}
