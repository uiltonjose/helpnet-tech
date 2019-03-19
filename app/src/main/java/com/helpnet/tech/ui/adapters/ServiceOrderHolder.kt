package com.helpnet.tech.ui.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.helpnet.tech.R

class ServiceOrderHolder(
    itemView: View,
    private val serviceOrderAdapter: ServiceOrderAdapter
) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    val tvNumberAndCustomer: TextView = itemView.findViewById(R.id.tvNumberAndCustomer)
    val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
    val tvDescription: TextView = itemView.findViewById(R.id.tv_description)
    val tvDate: TextView = itemView.findViewById(R.id.tv_date)

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
//        val notification = serviceOrderAdapter.listNotification[adapterPosition]
//        val notificationJson = Gson().toJson(notification)
//
//        Intent(activity, NotificationDetailActivity::class.java).apply {
//            putExtra("notificationJson", notificationJson)
//            activity.startActivity(this)
//        }
    }
}