package com.jake5113.malddongkt.main.list.toilet

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.jake5113.malddongkt.R
import com.jake5113.malddongkt.database.ToiletItem
import com.jake5113.malddongkt.main.MainActivity
import java.io.Serializable

class ToiletRecyclerAdapter(
    val context: Context,
    private val items: MutableList<ToiletItem>,
    private val sizeShort: Boolean
) :
    Adapter<VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val itemShortView: View =
            LayoutInflater.from(context).inflate(R.layout.item_short_recycler, parent, false)
        val itemLongView: View =
            LayoutInflater.from(context).inflate(R.layout.item_long_recycler, parent, false)
        return if (sizeShort) VH(itemShortView) else VH(itemLongView)
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: VH, position: Int) {

        val item = items[position]
        Glide.with(context).load(
            item.photo?.get(0)
                ?: "https://cdn.pixabay.com/photo/2020/03/27/17/03/shopping-4974313__340.jpg"
        ).into(holder.ivImg)
        holder.tvName.text = item.toiletNm
        holder.tvAddress.text = item.lnmAdres
        holder.tvDistance.text =
            try {
                (context as MainActivity).myLocation?.distanceTo(Location(LocationManager.GPS_PROVIDER).apply {
                    latitude = item.laCrdnt.toDouble()
                    longitude = item.loCrdnt.toDouble()
                })?.toInt().toString() + "m"
            } catch (e: Exception) {
                ""
            }

        // 좋아요 선택되어있는 경우에만 체크표시
        holder.btnFavorite.isChecked = item.isFavorite
        holder.btnFavorite.setOnClickListener {

        }

        // 해당 아이템 클릭시 상세페이지에 데이터 전달 및 뷰 이동
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ToiletDetailActivity::class.java)
            intent.putExtra("toiletItem", item as Serializable)
            context.startActivity(intent)
        }
    }
}

class VH(itemView: View) : ViewHolder(itemView) {
    val ivImg: ImageView by lazy { itemView.findViewById(R.id.iv_img) }
    val tvName: TextView by lazy { itemView.findViewById(R.id.tv_name) }
    val tvAddress: TextView by lazy { itemView.findViewById(R.id.tv_address) }
    val tvDistance: TextView by lazy { itemView.findViewById(R.id.tv_distance) }
    val btnFavorite: ToggleButton by lazy { itemView.findViewById(R.id.btn_favorite) }
}