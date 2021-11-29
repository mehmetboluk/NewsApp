package com.mehmetboluk.newsapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mehmetboluk.newsapp.adapter.NewsAdapter
import com.mehmetboluk.newsapp.databinding.FragmentNewsBinding
import com.mehmetboluk.newsapp.repository.NewsViewModel
import com.mehmetboluk.newsapp.sourcesNetwork.modelNews.Article

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private lateinit var adapter: NewsAdapter
    private var newsViewModel  = NewsViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var sourceQuery: String = ""
        val newsArray = ArrayList<Article>()

        arguments?.let {
            with(it.get("item") as String) {
                sourceQuery = this
            }
        }

        binding.rcNewsFragment.layoutManager = LinearLayoutManager(context)
        adapter = NewsAdapter(newsArray, newsViewModel)

        arguments?.let {
            with(it.get("item") as String) {
                sourceQuery = this
            }
        }

        newsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        newsViewModel.makeApiCallForNews(sourceQuery)
        newsViewModel.newsObserver().observe(viewLifecycleOwner, Observer {
            newsArray.addAll(it.articles)
            adapter.notifyDataSetChanged()
            binding.rcNewsFragment.adapter = adapter
        })



    }

}