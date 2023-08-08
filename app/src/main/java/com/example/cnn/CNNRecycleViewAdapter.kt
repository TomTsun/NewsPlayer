package com.example.cnn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CNNRecycleViewAdapter(
    data: List<NewsData>,
    val listener: RecyclerViewClickListener
) : RecyclerView.Adapter<CNNRecycleViewAdapter.viewHolder>() {

    var news = listOf<NewsData>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    interface RecyclerViewClickListener {
        fun onItemClick(view: View, position: Int)
    }

    class viewHolder(val view: View, val listener: RecyclerViewClickListener) :
        RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.textView)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_list_item, null)

        return viewHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.textView.text = news[position].title
        holder.imageView.setImageBitmap(news[position].cover)
        holder.view.setOnClickListener {
            listener.onItemClick(it, position)
        }
    }


}