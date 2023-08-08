package com.example.cnn

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.net.URL

class NewsXMLParser (val listener: ParserListener){
    private val factory = XmlPullParserFactory.newInstance() //no worry the version even OS
    private val parser = factory.newPullParser()
    private var text = ""
    private var newsTitle=""
    private val data= mutableListOf<NewsData>()
    private var imageFound = false

    fun parseURL(url: String){

        var text = ""
        var newsTitle = ""
        var newsCover: Bitmap? = null
        var newsUrl=""
        var newsDescription=""

        listener.start()
        GlobalScope.launch{
            try{
                val inputStream = URL(url).openStream()
                parser.setInput(inputStream, null)
                var eventType = parser.next()

                while(eventType != XmlPullParser.END_DOCUMENT){
                    var tagName = parser.name
                    if(tagName.equals("entry", ignoreCase = true) && eventType == XmlPullParser.START_TAG) {
                        while(!(tagName.equals("entry", ignoreCase = true) && eventType == XmlPullParser.END_TAG)){
                            when(eventType){
                                XmlPullParser.START_TAG->if(tagName.equals("media:thumbnail", ignoreCase = true)){
                                    val url = parser.getAttributeValue(null, "url")
                                    Log.i("URL", url)
                                    val inputStream = URL(url).openStream()
                                    newsCover = BitmapFactory.decodeStream(inputStream)

                                }

                                XmlPullParser.TEXT->text=parser.text
                                XmlPullParser.END_TAG->if(tagName.equals("title", ignoreCase = true)){
                                    newsTitle = text
                                }else if(tagName.equals("media:description", ignoreCase = true)){
                                    newsDescription = text

                                }else if(tagName.equals("yt:videoId", ignoreCase = true)){
                                    newsUrl = text
                                    Log.i("Key", newsUrl)
                                }
                            }
                            eventType=parser.next()
                            tagName=parser.name
                        }
                        data.add(NewsData(newsTitle, newsCover,newsUrl,newsDescription))
                        Log.i("title", newsTitle)
                    }
                    eventType = parser.next()
                }
                withContext(Dispatchers.Main){//切回ui
                    listener.finish(data)
                }

            } catch(e: Throwable){
                e.printStackTrace()
            }
        }

    }
}