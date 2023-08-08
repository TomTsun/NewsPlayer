package com.example.cnn

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {
    val newsList = MutableLiveData<List<NewsData>>()
    val isRefreshing = MutableLiveData<Boolean>()

    val selectedNews = MutableLiveData<NewsData>()

    fun selectNews(index: Int){
        newsList.value?.let{
            if(index >= 0 && index < it.size){
                selectedNews.value = it[index]
            }
        }
    }

    fun loadList() {
        newsList.value?.let{
            if(it.size>0) return
        }
        NewsXMLParser(object : ParserListener {
            override fun start() {
                isRefreshing.value = true
            }

            override fun finish(piece: List<NewsData>) {
                newsList.value = piece
                isRefreshing.value = false
            }

        }).parseURL(
            "http://www.youtube.com/feeds/videos.xml?channel_id=UCupvZG-5ko_eiXAupbDfxWw"
        )
    }

    fun reloadList() {
        newsList.value = listOf<NewsData>()
        loadList()
    }

}