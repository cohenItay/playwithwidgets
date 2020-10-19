package com.hit.itaycohen.intentsandactions.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hit.itaycohen.intentsandactions.R

class StupidTextAdapter : RecyclerView.Adapter<StupidTextAdapter.ViewHolder>() {

    var rowsAmount: Int = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.stupid_text_layout, parent, false) as TextView
        return ViewHolder(view)
    }

    override fun getItemCount() = rowsAmount

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder.itemView as TextView).apply{
            text = resources.getString(R.string.thatsAStupidText, position)
        }
    }

    class ViewHolder(itemView: TextView) : RecyclerView.ViewHolder(itemView)
}
