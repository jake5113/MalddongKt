package com.jake5113.malddongkt.main.list.touristspot

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.jake5113.malddongkt.R
import com.jake5113.malddongkt.main.list.toilet.ToiletDetailActivity
import com.jake5113.malddongkt.main.list.toilet.VH
import java.io.Serializable

class TouristSpotRecyclerAdapter(val context: Context, private val items: MutableList<TouristSpotItem>, private val sizeShort:Boolean) :
    Adapter<VH>() {
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

        Glide.with(context).load(if(item.img_path != "") item.img_path else R.drawable.malddong).into(holder.ivImg)
        holder.tvName.text = item.title
        holder.tvAddress.text = item.introduction
        holder.tvDistance.text = "15m"

        // 해당 아이템 클릭시 상세페이지에 데이터 전달 및 뷰 이동
        holder.itemView.setOnClickListener {
            val intent = Intent(context, TouristSpotDetailActivity::class.java)
            intent.putExtra("touristItem", item as Serializable)
            context.startActivity(intent)
        }
    }
}

