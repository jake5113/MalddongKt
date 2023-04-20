package com.jake5113.malddongkt.main.list.parking

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jake5113.malddongkt.R
import com.jake5113.malddongkt.databinding.ActivityParkingDetailBinding
import com.jake5113.malddongkt.main.list.toilet.ToiletItem
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker

class ParkingDetailActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityParkingDetailBinding

    lateinit var parkingNm: String
    lateinit var latLng: LatLng

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParkingDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.parking_map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.parking_map, it).commit()
            }
        mapFragment.getMapAsync(this)

        val parkingItem: ParkingItem = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("parkingItem", ParkingItem::class.java) as ParkingItem
        } else {
            intent.getParcelableExtra("parkingItem")!!
        }

        latLng = try {
            LatLng(parkingItem.latitude.toDouble(), parkingItem.longitude.toDouble())
        } catch (e: Exception) {
            // 좌표중 비어 있는 값 처리.
            LatLng(0.0, 0.0)
        }

        parkingNm = parkingItem.name

        with(binding) {
            parkingName.text = parkingItem.name
            rnAdres.text = parkingItem.rnAdres
            lnmAdres.text = parkingItem.lnmAdres
            operatingDays.text = "운영요일 : " + parkingItem.operatingDays
            enableNum.text = "주차구획수 : " + parkingItem.enableNum
            manageName.text ="관리기관명 : " +  parkingItem.manageName
            phoneNo.text = "연락처 : " + parkingItem.phoneNo
            price.text = "요금정보 : " + parkingItem.price
            isPublic.text = "구분 : " + parkingItem.isPublic
            type.text = "유형 : " + parkingItem.type
        }
    }

    override fun onMapReady(naverMap: NaverMap) {
        // 줌 버튼 삭제
        //naverMap.uiSettings.isZoomControlEnabled = false

        val infoWindow = InfoWindow()
        val cameraUpdate = CameraUpdate.scrollTo(latLng)
        infoWindow.position = latLng
        infoWindow.adapter = object : InfoWindow.DefaultTextAdapter(this) {
            override fun getText(infoWindow: InfoWindow): CharSequence =
                if (latLng.latitude == 0.0) "위치정보없음" else parkingNm
        }
        naverMap.moveCamera(cameraUpdate)
        infoWindow.alpha = 0.8f
        infoWindow.open(naverMap)
    }
}