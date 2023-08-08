package com.example.cnn

interface ParserListener {
    fun start()
    fun finish(News: List<NewsData>)
}
