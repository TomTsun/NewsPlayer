package com.example.cnn

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.cnn.databinding.ActivitySwipeRefreshBinding
import com.example.cnn.databinding.FragmentNewsListBinding


class newsListFragment : Fragment(), CNNRecycleViewAdapter.RecyclerViewClickListener {
    private val model: SharedViewModel by activityViewModels()

    lateinit var binding: FragmentNewsListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = activity
        binding.model = model

        val recyclerView = binding.recyclerView

        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
        )
        model.loadList()
    }

    val adapter: CNNRecycleViewAdapter by lazy {
        CNNRecycleViewAdapter(listOf<NewsData>(), this)
    }

    val swipeRefreshLayout by lazy {
        binding.swipeRefreshLayout
    }


    override fun onItemClick(view: View, position: Int) {
        model.selectNews(position)

        if(parentFragment != null){
            val action = newsListFragmentDirections.actionNewsListFragmentToNewsPreviewFragment()
            findNavController().navigate(action)
        }
    }


}