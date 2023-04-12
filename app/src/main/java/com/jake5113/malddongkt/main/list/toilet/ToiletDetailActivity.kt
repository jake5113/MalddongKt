package com.jake5113.malddongkt.main.list.toilet

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.jake5113.malddongkt.databinding.ActivityToiletDetailBinding

class ToiletDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityToiletDetailBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToiletDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toiletItem = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("toiletItem", ToiletItem::class.java) as ToiletItem
        } else {
            intent.getSerializableExtra("toiletItem") as ToiletItem
        }

        Glide.with(this).load(toiletItem.photo?.get(0) ?: "https://cdn.pixabay.com/photo/2020/03/27/17/03/shopping-4974313__340.jpg").into(binding.ivToilet)
        binding.toiletNm.text = toiletItem.toiletNm
        binding.rnAdres.text = toiletItem.rnAdres
        binding.lnmAdres.text = toiletItem.lnmAdres
        binding.opnTimeInfo.text = toiletItem.opnTimeInfo
        binding.mngrInsttNm.text = "관리 기관 : ${toiletItem.mngrInsttNm}"
        binding.toiletPosesnSeNm.text = "화장실 소유 : ${toiletItem.toiletPosesnSeNm}"
        binding.telno.text = "전화번호 : ${toiletItem.telno}"
        binding.maleClosetCnt.text = "남성 대변기 수 : ${toiletItem.maleClosetCnt}"
        binding.maleUrinalCnt.text = "남성 소변기 수 : ${toiletItem.maleUrinalCnt}"
        binding.maleDspsnClosetCnt.text = "남성 장애인 대변기 수 : ${toiletItem.maleDspsnClosetCnt}"
        binding.maleDspsnUrinalCnt.text = "남성 장애인 소변기 수 : ${toiletItem.maleDspsnUrinalCnt}"
        binding.maleChildClosetCnt.text = "남성 어린이 대변기 수 : ${toiletItem.maleChildClosetCnt}"
        binding.maleChildUrinalCnt.text = "남성 어린이 소변기 수 : ${toiletItem.maleChildUrinalCnt}"
        binding.femaleClosetCnt.text = "여성 대변기 수 : ${toiletItem.femaleClosetCnt}"
        binding.femaleDspsnClosetCnt.text = "여성 장애인 대변기 수 : ${toiletItem.femaleDspsnClosetCnt}"
        binding.femaleChildClosetCnt.text = "여성 어린이 대변기 수 : ${toiletItem.femaleChildClosetCnt}"

    }
}