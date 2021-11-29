package com.mehmetboluk.newsapp.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mehmetboluk.newsapp.R
import com.mehmetboluk.newsapp.databinding.HomeFragmentRowBinding
import com.mehmetboluk.newsapp.sourcesNetwork.modelSources.Source


class HomeAdapter (val source : ArrayList<Source>) : RecyclerView.Adapter<HomeAdapter.VH>() {


    class VH(val binding : HomeFragmentRowBinding) : RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {

        return VH(HomeFragmentRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.tvHomeDescription.text = source.get(position).description
        holder.binding.tvHomeName.text = source.get(position).name
        holder.itemView.setOnClickListener {
            Navigation.findNavController(holder.itemView).navigate(R.id.action_homeFragment_to_newsFragment, bundleOf("item" to source.get(position).id))
        }
    }

    override fun getItemCount(): Int {
        return source.size
    }
}