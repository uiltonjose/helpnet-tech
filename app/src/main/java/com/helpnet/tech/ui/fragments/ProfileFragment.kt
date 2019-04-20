package com.helpnet.tech.ui.fragments

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.helpnet.tech.R
import com.helpnet.tech.ui.activities.LoginActivity
import com.helpnet.tech.util.AlertDialogUtil

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_profile, container, false)
        initViews(rootView)
        return rootView
    }

    private fun initViews(rootView: View) {
        rootView.findViewById<Button>(R.id.logoutButton).setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        AlertDialogUtil.showMessageOKCancel(context,
            getString(R.string.logout),
            getString(R.string.logout_message),
            okListener = DialogInterface.OnClickListener { _, _ ->
                doLogout()
            })
    }

    private fun doLogout() {
        FirebaseAuth.getInstance().signOut()

        Intent(activity, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(this)
            activity!!.finish()
        }
    }
}
