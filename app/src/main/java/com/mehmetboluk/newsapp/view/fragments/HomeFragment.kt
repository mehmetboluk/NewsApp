package com.mehmetboluk.newsapp.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mehmetboluk.newsapp.R
import com.mehmetboluk.newsapp.adapter.HomeAdapter
import com.mehmetboluk.newsapp.databinding.FragmentHomeBinding
import com.mehmetboluk.newsapp.repository.NewsViewModel
import com.mehmetboluk.newsapp.sourcesNetwork.modelSources.Source


class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private var newsViewModel  = NewsViewModel()
    private lateinit var adapter : HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvHome.layoutManager = LinearLayoutManager(context)
        val sourceArray = ArrayList<Source>()

        newsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        newsViewModel.makeApiCallForSource("us")
        newsViewModel.sourcesObserver().observe(viewLifecycleOwner, Observer {
            sourceArray.addAll(it.sources)
            Log.d("Source", sourceArray.size.toString())
            adapter = HomeAdapter(sourceArray)
            adapter.notifyDataSetChanged()
            binding.rvHome.adapter = adapter
        })


    }


}