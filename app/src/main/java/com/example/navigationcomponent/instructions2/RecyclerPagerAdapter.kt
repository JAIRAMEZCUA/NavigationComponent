package com.example.navigationcomponent.instructions2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationcomponent.R

class RecyclerPagerAdapter(private val stringArray: Array<String>) :
    RecyclerView.Adapter<RecyclerPagerAdapter.StringViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_string, parent, false)
        return StringViewHolder(view)
    }

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
        holder.bind(stringArray[position])
    }

    override fun getItemCount(): Int {
        return stringArray.size
    }

    inner class StringViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.textView)
        fun bind(text: String) {
            textView.text = text
        }
    }
}