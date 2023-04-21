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
import com.jake5113.malddongkt.main.list.parking.ParkingItem
import com.jake5113.malddongkt.main.list.toilet.ToiletItem
import com.jake5113.malddongkt.main.list.touristspot.TouristSpotItem
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.MarkerIcons

class NaverMapFragment : Fragment(), OnMapReadyCallback {
    lateinit var binding: FragmentNaverMapBinding
    private var myLocation: LatLng? = LatLng(37.5667, 126.9783)
    fun getMyLocation(location: Location){
        myLocation = LatLng(location.latitude, location.longitude)
    }

    val totalItemsToilet: MutableList<ToiletItem> = mutableListOf()
    val totalItemsTourist: MutableList<TouristSpotItem> = mutableListOf()
    val totalItemsParking: MutableList<ParkingItem> = mutableListOf()

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
        val myMarker = Marker()
        myMarker.position = myLocation!!
        myMarker.icon = MarkerIcons.RED
        myMarker.map = naverMap

        totalItemsToilet.forEach {
            try {
                val toiletMarker = Marker()
                toiletMarker.position = LatLng(it.laCrdnt.toDouble(), it.loCrdnt.toDouble())
                toiletMarker.captionText = it.toiletNm
                toiletMarker.captionOffset = 30
                toiletMarker.width = Marker.SIZE_AUTO
                toiletMarker.height = Marker.SIZE_AUTO
                toiletMarker.icon = MarkerIcons.YELLOW
                toiletMarker.map = naverMap
            } catch (e:Exception){
                return@forEach
            }
        }

        totalItemsTourist.forEach {
            try {
                val touristMarker = Marker()
                touristMarker.position = LatLng(it.latitude.toDouble(), it.longitude.toDouble())
                touristMarker.captionText = it.title
                touristMarker.captionOffset = 30
                touristMarker.width = Marker.SIZE_AUTO
                touristMarker.height = Marker.SIZE_AUTO
                touristMarker.icon = MarkerIcons.GREEN
                touristMarker.map = naverMap
            } catch (e:Exception){
                return@forEach
            }
        }

        totalItemsParking.forEach {
            try {
                val parkingMarker = Marker()
                parkingMarker.position = LatLng(it.latitude.toDouble(), it.longitude.toDouble())
                parkingMarker.captionText = it.name
                parkingMarker.captionOffset = 30
                parkingMarker.width = Marker.SIZE_AUTO
                parkingMarker.height = Marker.SIZE_AUTO
                parkingMarker.icon = MarkerIcons.BLUE
                parkingMarker.map = naverMap
            } catch (e:Exception){
                return@forEach
            }
        }

        naverMap.moveCamera(CameraUpdate.scrollTo(myMarker.position))

        // 내 위치 버튼 누를 때
        binding.ibMyLocation.setOnClickListener {
            (activity as MainActivity).requestMyLocation()
            naverMap.moveCamera(CameraUpdate.scrollTo(myMarker.position))
        }
    }
}