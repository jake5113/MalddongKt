package com.jake5113.malddongkt.main.list.toilet

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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

    lateinit var toiletNm: String
    lateinit var latLng: LatLng

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
        latLng =
            try {
                LatLng(toiletItem.laCrdnt.toDouble(), toiletItem.loCrdnt.toDouble())
            } catch (e:Exception){
                // 좌표중 비어 있는 값 처리.
                LatLng(0.0,0.0)
            }

        // 뷰페이저에서 아이템 3개를 미리 로드하도록 하는 명령
        binding.vpToiletImg.offscreenPageLimit = 3
        // 뷰페이저 어댑터 설정
        binding.vpToiletImg.adapter = ToiletDetailViewPagerAdapter(this, toiletItem.photo)

        if(toiletItem.photo.isNullOrEmpty()){
            binding.btnBack.visibility = View.GONE
            binding.btnForward.visibility = View.GONE
        } else if (toiletItem.photo?.size!! <= 1) {
            binding.btnBack.visibility = View.GONE
            binding.btnForward.visibility = View.GONE
        } else {
            // 좌우 화살표 버튼 클릭시 뷰페이저 화면 전환
            binding.btnBack.setOnClickListener {
                val currentItem = binding.vpToiletImg.currentItem
                if (currentItem != 0) binding.vpToiletImg.setCurrentItem(currentItem - 1, true)
            }
            binding.btnForward.setOnClickListener {
                val currentItem = binding.vpToiletImg.currentItem
                if (currentItem + 1 != toiletItem.photo?.size) binding.vpToiletImg.setCurrentItem(currentItem + 1, true)
            }
        }

        // 화장실 상세 페이지 정보 설정.
        with(binding) {
            toiletNm.text = toiletItem.toiletNm
            rnAdres.text = toiletItem.rnAdres
            lnmAdres.text = toiletItem.lnmAdres
            opnTimeInfo.text = toiletItem.opnTimeInfo
            mngrInsttNm.text = "관리 기관 : ${toiletItem.mngrInsttNm}"
            toiletPosesnSeNm.text = "화장실 소유 : ${toiletItem.toiletPosesnSeNm}"
            telno.text = "전화번호 : ${toiletItem.telno}"
            maleClosetCnt.text = "남성 대변기 수 : ${toiletItem.maleClosetCnt}"
            maleUrinalCnt.text = "남성 소변기 수 : ${toiletItem.maleUrinalCnt}"
            maleDspsnClosetCnt.text = "남성 장애인 대변기 수 : ${toiletItem.maleDspsnClosetCnt}"
            maleDspsnUrinalCnt.text = "남성 장애인 소변기 수 : ${toiletItem.maleDspsnUrinalCnt}"
            maleChildClosetCnt.text = "남성 어린이 대변기 수 : ${toiletItem.maleChildClosetCnt}"
            maleChildUrinalCnt.text = "남성 어린이 소변기 수 : ${toiletItem.maleChildUrinalCnt}"
            femaleClosetCnt.text = "여성 대변기 수 : ${toiletItem.femaleClosetCnt}"
            femaleDspsnClosetCnt.text = "여성 장애인 대변기 수 : ${toiletItem.femaleDspsnClosetCnt}"
            femaleChildClosetCnt.text = "여성 어린이 대변기 수 : ${toiletItem.femaleChildClosetCnt}"
        }
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
            override fun getText(infoWindow: InfoWindow): CharSequence = if (latLng.latitude == 0.0) "위치정보없음" else toiletNm
        }
        naverMap.moveCamera(cameraUpdate)
        infoWindow.alpha = 0.8f
        infoWindow.open(naverMap)
    }
}
