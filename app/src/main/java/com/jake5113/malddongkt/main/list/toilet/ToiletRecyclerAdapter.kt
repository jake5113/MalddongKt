package com.jake5113.malddongkt.main.list.toilet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.jake5113.malddongkt.R

class ToiletRecyclerAdapter(val context: Context, private val items: MutableList<ToiletItem>, private val sizeShort:Boolean) :
    Adapter<ToiletRecyclerAdapter.VH>() {
    inner class VH(itemView: View) : ViewHolder(itemView) {
        val ivImg : ImageView by lazy { itemView.findViewById(R.id.iv_img) }
        val tvName: TextView by lazy { itemView.findViewById(R.id.tv_name) }
        val tvAddress: TextView by lazy { itemView.findViewById(R.id.tv_address) }
        val tvDistance: TextView by lazy { itemView.findViewById(R.id.tv_distance) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val itemShortView: View =
            LayoutInflater.from(context).inflate(R.layout.item_short_recycler, parent, false)
        val itemLongView: View =
            LayoutInflater.from(context).inflate(R.layout.item_long_recycler, parent, false)
        return if(sizeShort) VH(itemShortView) else VH(itemLongView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]

        Glide.with(context).load(item.photo[0]).into(holder.ivImg)
        holder.tvName.text = item.toiletNm
        holder.tvAddress.text = item.lnmAdres
        holder.tvDistance.text = "15m"
    }
}
