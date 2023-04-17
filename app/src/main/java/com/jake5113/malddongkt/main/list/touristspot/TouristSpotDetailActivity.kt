package com.jake5113.malddongkt.main.list.touristspot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jake5113.malddongkt.R
import android.annotation.SuppressLint
import android.os.Build
import com.bumptech.glide.Glide
import com.jake5113.malddongkt.databinding.ActivityTouristSpotDetailBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.InfoWindow


class TouristSpotDetailActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityTouristSpotDetailBinding

    lateinit var touristNm: String
    lateinit var latLng: LatLng

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTouristSpotDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.detail_map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.detail_map, it).commit()
            }

        mapFragment.getMapAsync(this)

        val toiletItem = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(
                "touristItem",
                TouristSpotItem::class.java
            ) as TouristSpotItem
        } else {
            intent.getSerializableExtra("touristItem") as TouristSpotItem
        }

        // 화장실 상세 페이지 정보 설정.
        with(binding) {
            Glide.with(this@TouristSpotDetailActivity).load(toiletItem.img_path).into(ivTouristImg)
            touristNm.text = toiletItem.title
            rnAdres.text = toiletItem.address
            lnmAdres.text = toiletItem.road_address
            phoneNo.text = toiletItem.phone_no
            tag.text = toiletItem.tag
            introduction.text = toiletItem.introduction
        }

        touristNm = toiletItem.title
        latLng =
            try {
                LatLng(toiletItem.latitude.toDouble(), toiletItem.longitude.toDouble())
            } catch (e: Exception) {
                // 좌표중 비어 있는 값 처리.
                LatLng(0.0, 0.0)
            }
    }

    override fun onMapReady(naverMap: NaverMap) {

        // 줌 버튼 삭제
        naverMap.uiSettings.isZoomControlEnabled = false

        val infoWindow = InfoWindow()
        val cameraUpdate = CameraUpdate.scrollTo(latLng)
        infoWindow.position = latLng
        infoWindow.adapter = object : InfoWindow.DefaultTextAdapter(this) {
            override fun getText(infoWindow: InfoWindow): CharSequence =
                if (latLng.latitude == 0.0) "위치정보없음" else touristNm
        }
        naverMap.moveCamera(cameraUpdate)
        infoWindow.alpha = 0.8f
        infoWindow.open(naverMap)
    }
}
