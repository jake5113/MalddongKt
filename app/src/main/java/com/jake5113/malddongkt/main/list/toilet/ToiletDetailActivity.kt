package com.jake5113.malddongkt.main.list.toilet

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.jake5113.malddongkt.R
import com.jake5113.malddongkt.databinding.ActivityToiletDetailBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker

class ToiletDetailActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityToiletDetailBinding

    lateinit var toiletNm:String
    lateinit var latLng:LatLng
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToiletDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.detail_map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.detail_map, it).commit()
            }

        mapFragment.getMapAsync(this)

        val toiletItem = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("toiletItem", ToiletItem::class.java) as ToiletItem
        } else {
            intent.getSerializableExtra("toiletItem") as ToiletItem
        }

        toiletNm = toiletItem.toiletNm
        latLng = LatLng(toiletItem.laCrdnt.toDouble(), toiletItem.loCrdnt.toDouble())

        // 뷰페이저에서 아이템 3개를 미리 로드하도록 하는 명령
        binding.vpToiletImg.offscreenPageLimit = 3
        // 뷰페이저 어댑터 설정
        binding.vpToiletImg.adapter = ToiletDetailViewPagerAdapter(this, toiletItem.photo)

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

    override fun onMapReady(naverMap: NaverMap) {
        // 마커 생성 안함.
        //val marker = Marker()
        //marker.map = null

        // 줌 버튼 삭제
        naverMap.uiSettings.isZoomControlEnabled = false

        val infoWindow = InfoWindow()
        val cameraUpdate = CameraUpdate.scrollTo(latLng)
        infoWindow.position = latLng
        infoWindow.adapter = object : InfoWindow.DefaultTextAdapter(this) {
            override fun getText(infoWindow: InfoWindow): CharSequence = toiletNm
        }
        naverMap.moveCamera(cameraUpdate)
        infoWindow.alpha = 0.8f
        infoWindow.open(naverMap)
    }
}
