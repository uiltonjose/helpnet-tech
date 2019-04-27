package com.helpnet.tech.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.helpnet.tech.R

class NetworkScreenFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_network_screen, container, false)
        view.setOnClickListener {
            //dummyClick. To avoid click below of fragment.
        }

        view.findViewById<View>(R.id.closeButton).setOnClickListener {
            dismissFragment()
        }

        return view
    }

    private fun dismissFragment() {
        val fm = activity!!.supportFragmentManager
        val fragment = fm.findFragmentByTag(TAG)
        fragment?.also {
            val ft = fm.beginTransaction()
            ft.remove(it)
            ft.commit()
        }
    }

    companion object {

        const val TAG = "NetworkScreenFragment"
    }
}
