package com.helpnet.tech.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.helpnet.tech.R
import com.helpnet.tech.data.model.OSsimple
import com.helpnet.tech.util.ParseUtil

class ServiceOrderAdapter constructor(
    val listOS: List<OSsimple>,
    private val isInProgress: Boolean = false
) : RecyclerView.Adapter<ServiceOrderHolder>() {

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceOrderHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.list_os_item, null)
        return ServiceOrderHolder(viewHolder, this, isInProgress)
    }

    override fun getItemCount(): Int {
        return listOS.size
    }

    override fun onBindViewHolder(holder: ServiceOrderHolder, position: Int) {
        val os = listOS[position]
        holder.tvNumberAndCustomer.text = "${os.number} - ${os.name}"
        holder.tvTitle.text = os.problem
        holder.tvDescription.text = os.detail
        holder.tvDate.text = ParseUtil.formatDateString(os.dateOpen)
    }
}