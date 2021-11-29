package com.mehmetboluk.newsapp.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mehmetboluk.newsapp.adapter.FavoriteAdapter.*
import com.mehmetboluk.newsapp.databinding.FavoriteFragmentRowBinding
import com.mehmetboluk.newsapp.repository.NewsViewModel
import com.mehmetboluk.newsapp.sourcesNetwork.modelNews.Article


class FavoriteAdapter(private var newsArray: ArrayList<Article>, private val newsViewModel: NewsViewModel) : RecyclerView.Adapter<ViewHolder>() {


    class ViewHolder(val binding : FavoriteFragmentRowBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FavoriteFragmentRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(newsArray.get(position).urlToImage).into(holder.binding.ivFavoriteIcon)
        holder.binding.tvFavoriteContent.text = newsArray.get(position).content
        holder.binding.tvFavoriteTitle.text = newsArray.get(position).title
        holder.binding.tvFavoritePublish.text = newsArray.get(position).publishedAt
        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(newsArray.get(position).url))
            holder.itemView.context.startActivity(intent)
        }
        holder.binding.tvDeleteButtonFromFav.setOnClickListener {
            newsViewModel.deleteData(holder.itemView.context, newsArray.get(position))
            newsArray.remove(newsArray.get(position))
            notifyDataSetChanged()
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount(): Int {
        return newsArray.size
    }

}