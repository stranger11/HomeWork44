package com.example.homework4

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class ColorsAdapter (private var colors: List<Int>, val context: Context)
    : RecyclerView.Adapter<ColorsAdapter.ColorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_color, parent, false)
        return ColorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bind(colors[position], context)
    }

    override fun getItemCount(): Int = colors.size

    class ColorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var root: View = view.findViewById(R.id.color)

        fun bind(color:Int, context: Context) {
            root.setBackgroundColor(color)
            root.setOnClickListener {
                val i = Intent(context, ContentActivity::class.java).apply {
                    putExtra("color", color)
                }
                context.startActivity(i)
            }
        }
    }
}