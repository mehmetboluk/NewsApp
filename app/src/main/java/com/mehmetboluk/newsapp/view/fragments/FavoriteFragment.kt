package com.mehmetboluk.newsapp.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mehmetboluk.newsapp.adapter.FavoriteAdapter
import com.mehmetboluk.newsapp.databinding.FragmentFavoriteBinding
import com.mehmetboluk.newsapp.repository.NewsViewModel
import com.mehmetboluk.newsapp.sourcesNetwork.modelNews.Article


class FavoriteFragment : Fragment(){

    private lateinit var adapter: FavoriteAdapter
    private lateinit var binding: FragmentFavoriteBinding
    private var newsViewModel  = NewsViewModel()
    private var newArray : ArrayList<Article> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.rvFavoriteNews.layoutManager = LinearLayoutManager(requireContext())



        newsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        newArray =  newsViewModel.callDataFromRoom(requireContext())
        Log.d("Article", newArray.size.toString())
        adapter = FavoriteAdapter(newArray, newsViewModel)
        adapter.notifyDataSetChanged()
        binding.rvFavoriteNews.adapter = adapter

    }

}