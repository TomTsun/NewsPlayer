package com.example.cnn

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.MediaController
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.cnn.databinding.ActivityPreviewBinding
import com.example.cnn.databinding.FragmentNewsPreviewBinding
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import java.text.FieldPosition


class newsPreviewFragment : Fragment() {
    private val YOUTUBE_API_KEY = "AIzaSyDPKDIYi3YLn9pDv6_ZBiGrHeOZR8RK8zQ"

    private val model: SharedViewModel by activityViewModels()


    private var title: String? = ""
    private var url: String? = ""
    private var description: String? = ""

    //var youtubePlayer: YouTubePlayer? = null

    //lateinit var binding: FragmentNewsPreviewBinding

    //val args: newsPreviewFragmentArgs by navArgs<newsPreviewFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.selectedNews.observe(viewLifecycleOwner, Observer {
            previewNews(it.title, it.description, it.url)
        })
    }

    companion object{
        var myview : View? = null
        private lateinit var binding:FragmentNewsPreviewBinding
        //private var currentnewsUrl: String? = null
        private var youtubePlayer: YouTubePlayer? = null
    }

    //僅inflate layout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (myview!=null) return myview

        binding = FragmentNewsPreviewBinding.inflate(inflater)
        myview = binding.root
        return binding.root
    }

    fun previewNews(newsTitle: String, newsDescription: String, newsUrl: String) {
        //youtubePlayer?.release()


        binding.title = newsTitle
        binding.description = newsDescription
        //currentnewsUrl=newsUrl

        val videoFragment =
            activity?.fragmentManager?.findFragmentById(R.id.playerFragment) as YouTubePlayerFragment?

        videoFragment?.let {
            activity?.fragmentManager?.beginTransaction()?.remove(videoFragment)?.commit()
        }
        //val playerFragment = youtubeFragment?.getPlayer()


        if (youtubePlayer != null) {
            youtubePlayer?.loadVideo(newsUrl)
            //youtubePlayer!!.play()
            youtubePlayer = youtubePlayer
        }

        videoFragment?.initialize(YOUTUBE_API_KEY, object : YouTubePlayer.OnInitializedListener {

            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                p1?.loadVideo(newsUrl)
                //p1?.play()
                youtubePlayer = p1
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                //Toast.makeText("applicationContext", "Youtube init failed", Toast.LENGTH_LONG).show()
                Log.i("Youtube Player", "Failed to initialize");
            }
        })

    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

//    fun onPreviewClicked(view: View) {
//        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
//        //Log.i("Key")
//        startActivity(intent)
//    }

    override fun onDestroyView() {
        super.onDestroyView()
       //youtubePlayer=null
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }


}