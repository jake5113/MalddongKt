package com.jake5113.malddongkt.main.map

import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jake5113.malddongkt.R
import com.jake5113.malddongkt.databinding.FragmentNaverMapBinding
import com.jake5113.malddongkt.main.MainActivity
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker

class NaverMapFragment : Fragment(), OnMapReadyCallback {
    lateinit var binding: FragmentNaverMapBinding
    private var myLocation: LatLng? = LatLng(37.5667, 126.9783)
    fun getMyLocation(location: Location){
        myLocation = LatLng(location.latitude, location.longitude)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNaverMapBinding.inflate(inflater, container, false)

        val fragmentManager = childFragmentManager
        val mapFragment = fragmentManager.findFragmentById(R.id.navermap_container) as MapFragment?
            ?: MapFragment.newInstance().also {
                fragmentManager.beginTransaction().add(R.id.navermap_container, it).commit()
            }

        mapFragment.getMapAsync(this)

        return binding.root
    }

    override fun onMapReady(naverMap: NaverMap) {

        // 내 위치 마커
        val marker = Marker()
        marker.position = myLocation!!
        marker.map = naverMap

        naverMap.moveCamera(CameraUpdate.scrollTo(marker.position))

        binding.ibMyLocation.setOnClickListener {
            (activity as MainActivity).requestMyLocation()
            naverMap.moveCamera(CameraUpdate.scrollTo(marker.position))
        }
    }
}