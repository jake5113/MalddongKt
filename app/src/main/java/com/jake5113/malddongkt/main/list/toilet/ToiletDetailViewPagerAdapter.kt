package com.jake5113.malddongkt.main.list.toilet

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.jake5113.malddongkt.databinding.ItemViewpagerBinding

class ToiletDetailViewPagerAdapter(var context: Context, var imgList: MutableList<String>?) :
    Adapter<ToiletDetailViewPagerAdapter.VH>() {
    inner class VH(val binding: ItemViewpagerBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(ItemViewpagerBinding.inflate(LayoutInflater.from(context), parent, false))

    override fun getItemCount(): Int = imgList?.size ?: 1
    override fun onBindViewHolder(holder: VH, position: Int) {
        Glide.with(context).load(imgList?.get(position) ?: "https://cdn.pixabay.com/photo/2020/03/27/17/03/shopping-4974313__340.jpg").into(holder.binding.img)
    }
}