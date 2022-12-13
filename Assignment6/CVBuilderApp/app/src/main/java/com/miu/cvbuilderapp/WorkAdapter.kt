package com.miu.cvbuilderapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.work_item_layout.view.*

class WorkAdapter(var blist: ArrayList<Work>): RecyclerView.Adapter<WorkAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //depending on the parent
        val view = LayoutInflater.from(parent.context).inflate(R.layout.work_item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //bind item from your source to viewholder
        holder.itemView.tvPosition.text = blist[position].position
        holder.itemView.tvCompany.text = blist[position].company
        holder.itemView.tvDuration.text = blist[position].duration
        holder.itemView.tvLocation.text = blist[position].location
        holder.itemView.tvDesc.text = blist[position].desc
        holder.itemView.img.setImageResource(blist[position].image)

    }

    override fun getItemCount(): Int {
        return blist.size
    }
}
