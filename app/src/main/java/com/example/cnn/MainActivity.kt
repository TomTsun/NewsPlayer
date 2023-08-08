package com.example.cnn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.cnn.databinding.FragmentNewsPreviewBinding


class MainActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //用onAttach
//        val listFragment = supportFragmentManager.findFragmentById(R.id.listFragment) as newsListFragment?
//        listFragment?.listener = object : newsListFragment.onNewsSelectedListener{
//            override fun onNewsSelected(title: String, description: String, url: String){
//                val previewFragment = supportFragmentManager.findFragmentById(R.id.previewFragment) as newsPreviewFragment?
//                previewFragment?.previewNews(title, description, url)
//
//            }
//        }
    }

//    override fun onNewsSelected(position: Int) {
//        val previewFragment =
//            supportFragmentManager.findFragmentById(R.id.previewFragment) as newsPreviewFragment?
//
//        if (previewFragment != null) {//means on tablet
//            previewFragment?.previewNews(title, description, url)
//        } else {
//            val action = newsListFragmentDirections.actionNewsListFragmentToNewsPreviewFragment(
//                title, description, url
//            )
//            findNavController(R.id.navHostFragment).navigate(action)
//        }
//    }

    override fun onBackPressed() {
        super.onBackPressed()

    }

}