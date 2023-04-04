package com.jake5113.malddongkt.main.list.toilet

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.jake5113.malddongkt.databinding.ItemLongRecyclerBinding
import com.jake5113.malddongkt.databinding.ItemShortRecyclerBinding

class ToiletLongRecyclerAdapter(val context: Context, val items: MutableList<ToiletItem>) :
    Adapter<ToiletLongRecyclerAdapter.VH>() {
    inner class VH(var binding: ItemLongRecyclerBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(ItemLongRecyclerBinding.inflate(LayoutInflater.from(context), parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]

        // 좋아요 값 저장 필요
        holder.binding.btnFavorite.isChecked = false

        // (파싱할때는)사진 유뮤 확인 코드 필요
        Glide.with(context).load(item.photo[0]).into(holder.binding.ivImg)
        holder.binding.tvName.text = item.toiletNm
        holder.binding.tvAddress.text = item.lnmAdres
        holder.binding.tvDistance.text = "15m"
    }
}
