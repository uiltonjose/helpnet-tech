package com.helpnet.tech.ui.adapters

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.helpnet.tech.R
import com.helpnet.tech.ui.activities.OpenServiceDetailActivity
import com.helpnet.tech.util.Constants.OS_NUMBER_PARAM
import com.helpnet.tech.util.Constants.OS_WIP_PARAM

class ServiceOrderHolder(
    itemView: View,
    private val serviceOrderAdapter: ServiceOrderAdapter,
    private val isInProgress: Boolean
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
        Intent(context, OpenServiceDetailActivity::class.java).apply {
            putExtra(OS_NUMBER_PARAM, orderService.number)
            putExtra(OS_WIP_PARAM, isInProgress)
            context.startActivity(this)
        }
    }
}