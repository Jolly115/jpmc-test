package com.app.a20230125_jollygupta_nycschools.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.app.a20230125_jollygupta_nycschools.R
import com.app.a20230125_jollygupta_nycschools.common.Utils
import com.app.a20230125_jollygupta_nycschools.model.SchoolDetail
import com.app.a20230125_jollygupta_nycschools.ui.home.HomeNavigator

class SchoolAdapter(val context: Context,schoolList: ArrayList<SchoolDetail>) :
    RecyclerView.Adapter<SchoolAdapter.MyViewHolder>() {

    var list = ArrayList<SchoolDetail>()
    var listner :HomeNavigator? = null
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvSchoolName: TextView = view.findViewById(R.id.tvSchoolName)
        var tvParagraph: TextView = view.findViewById(R.id.tvParagraph)
        var tvLocation: TextView = view.findViewById(R.id.tvLocation)
        var tvPhone: TextView = view.findViewById(R.id.tvPhone)
    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.school_list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder, position: Int
    ) {

        val model = list[position]
        holder.tvSchoolName.text = model.school_name
        holder.tvParagraph.text = model.overview_paragraph
        holder.tvPhone.text = model.phone_number
        holder.tvLocation.text = model.location
        holder.itemView.setOnClickListener {
            listner?.onDetailClick(model)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setItem(listData: ArrayList<SchoolDetail>) {
        list.clear()
        list.addAll(listData)
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: MyViewHolder) {
        super.onViewAttachedToWindow(holder)
        Utils.dismissProgressDialog()
    }
}
