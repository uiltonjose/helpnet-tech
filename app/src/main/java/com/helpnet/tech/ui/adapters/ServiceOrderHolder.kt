package com.helpnet.tech.ui.adapters

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.helpnet.tech.R
import com.helpnet.tech.ui.activities.OpenServiceDetail
import com.helpnet.tech.util.Constants.OS_NUMBER_PARAM

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
        val orderService = serviceOrderAdapter.listOS[adapterPosition]
        val context = itemView.context
        Intent(context, OpenServiceDetail::class.java).apply {
            putExtra(OS_NUMBER_PARAM, orderService.number)
            context.startActivity(this)
        }
    }
}