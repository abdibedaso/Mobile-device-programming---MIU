package com.miu.cvbuilderapp

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.text.HtmlCompat
import androidx.core.text.htmlEncode
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.home_card_layout.view.*

class HomeAdapter (var context: Context, var text1: Array<String>, var text2:Array<String>, var imgSrc:IntArray) : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.home_card_layout,parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.playout.apply{
            tv1?.text = text1[position]
            tv2?.text = HtmlCompat.fromHtml(text2[position], HtmlCompat.FROM_HTML_MODE_COMPACT)
            img.setImageResource(imgSrc[position])
        }
    }

    override fun getItemCount(): Int {
        return text1.size
    }

}
