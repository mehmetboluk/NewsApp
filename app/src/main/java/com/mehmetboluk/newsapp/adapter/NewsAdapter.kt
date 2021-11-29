package com.mehmetboluk.newsapp.adapter

import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mehmetboluk.newsapp.adapter.NewsAdapter.*
import com.mehmetboluk.newsapp.databinding.NewsFragmentRowBinding
import com.mehmetboluk.newsapp.repository.NewsViewModel
import com.mehmetboluk.newsapp.sourcesNetwork.modelNews.Article


class NewsAdapter(private var articles : ArrayList<Article>, private val newsViewModel: NewsViewModel) : RecyclerView.Adapter<NewsViewHolder>(){

    class NewsViewHolder(val binding: NewsFragmentRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            NewsFragmentRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(articles.get(position).urlToImage)
            .into(holder.binding.ivNewsIcon)
        holder.binding.newsTitle.text = articles.get(position).title
        holder.binding.newsContent.text = articles.get(position).content
        holder.binding.newsPublish.text = articles.get(position).publishedAt
        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(articles.get(position).url))
            holder.itemView.context.startActivity(intent)
        }
        holder.binding.tvFavoriteButton.setOnClickListener {
            newsViewModel.upsertData(holder.itemView.context, articles.get(position))
            holder.binding.tvFavoriteButton.visibility = View.GONE
            holder.binding.tvDeleteButton.visibility = View.VISIBLE
        }
        holder.binding.tvDeleteButton.setOnClickListener {
            newsViewModel.deleteData(holder.itemView.context, articles.get(position))
            holder.binding.tvDeleteButton.visibility = View.GONE
            holder.binding.tvFavoriteButton.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}