package com.example.wallhaven.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.wallhaven.Activity.Home_Activity
import com.example.wallhaven.Model.HitsItem
import com.example.wallhaven.R
import com.example.wallhaven.databinding.WallItemBinding

class WallpaperAdapter(val homeActivity: Home_Activity,val hits: List<HitsItem?>?):RecyclerView.Adapter<WallpaperAdapter.WallDataViewHolder>(){
    class WallDataViewHolder(itemView:View):ViewHolder(itemView){
        var binding : WallItemBinding = WallItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallDataViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.wall_item,parent,false)
        return WallDataViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hits!!.size
    }

    override fun onBindViewHolder(holder: WallDataViewHolder, position: Int) {
        Glide.with(homeActivity).load(hits!![position]!!.webformatURL).placeholder(R.drawable.ic_launcher_background).into(holder.binding.wallset)

    }

}