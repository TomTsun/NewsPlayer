package com.example.cnn//package com.example.cnn

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.Observable.OnPropertyChangedCallback
import androidx.databinding.ObservableField
import com.example.cnn.databinding.ActivityPreviewBinding
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView


class PreviewActivity : YouTubeBaseActivity() {
    private val YOUTUBE_API_KEY = "AIzaSyDPKDIYi3YLn9pDv6_ZBiGrHeOZR8RK8zQ"

    private var title: String? = ""
    private var url: String? = ""
    private var description: String? = ""
    private var isPlaying = false

    lateinit var binding: ActivityPreviewBindingActivityPreviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_preview)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_preview)
        val playerView = binding.playerView

        title = intent.getStringExtra("title")!! //表示一定不是null
        description = intent.getStringExtra("description")!!
        url = intent.getStringExtra("url")!!

        binding.title = title
        binding.description = description


        playerView.initialize(url, object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                p1?.loadVideo(url)
                p1?.play()
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(applicationContext, "Youtube init failed", Toast.LENGTH_LONG).show()
            }
        })

    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onSaveInstanceState(p0: Bundle) {
        super.onSaveInstanceState(p0)
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun onPreviewClicked(view: View) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        //Log.i("Key", url)
        startActivity(intent)
    }

}