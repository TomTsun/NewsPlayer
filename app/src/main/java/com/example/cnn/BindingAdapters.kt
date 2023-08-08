package com.example.cnn

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<NewsData>?) {
    val adapter = recyclerView.adapter as? CNNRecycleViewAdapter
    if (adapter != null && data != null) {
        adapter.news = data
    }
}