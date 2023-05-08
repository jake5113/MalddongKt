package com.jake5113.malddongkt.main.list.parking

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.jake5113.malddongkt.R
import com.jake5113.malddongkt.database.ParkingItem
import com.jake5113.malddongkt.main.MainActivity
import com.jake5113.malddongkt.main.list.toilet.VH


class ParkingRecyclerAdapter(
    val context: Context,
    private val items: MutableList<ParkingItem>,
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

        Glide.with(context)
            .load("https://cdn.pixabay.com/photo/2016/11/23/17/24/woman-1853936__340.jpg")
            .into(holder.ivImg)
        holder.tvName.text = item.name
        holder.tvAddress.text = item.lnmAdres
        holder.tvDistance.text =
            try {
                (context as MainActivity).myLocation?.distanceTo(
                    Location(LocationManager.GPS_PROVIDER).apply {
                        latitude = item.latitude.toDouble()
                        longitude = item.longitude.toDouble()
                    })?.toInt().toString() + "m"
            } catch (e: Exception) {
                ""
            }
        holder.btnFavorite.isChecked = item.isFavorite

        holder.btnFavorite.setOnClickListener {

        }

        // 해당 아이템 클릭시 상세페이지에 데이터 전달 및 뷰 이동
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ParkingDetailActivity::class.java)
            intent.putExtra("parkingItem", item as Parcelable)
            context.startActivity(intent)
        }
    }
}
