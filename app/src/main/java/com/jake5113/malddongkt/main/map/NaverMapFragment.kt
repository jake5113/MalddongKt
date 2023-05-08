package com.jake5113.malddongkt.main.map

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jake5113.malddongkt.R
import com.jake5113.malddongkt.databinding.FragmentNaverMapBinding
import com.jake5113.malddongkt.main.MainActivity
import com.jake5113.malddongkt.main.list.parking.ParkingDetailActivity
import com.jake5113.malddongkt.database.ParkingItem
import com.jake5113.malddongkt.main.list.toilet.ToiletDetailActivity
import com.jake5113.malddongkt.database.ToiletItem
import com.jake5113.malddongkt.main.list.touristspot.TouristSpotDetailActivity
import com.jake5113.malddongkt.database.TouristSpotItem
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay.OnClickListener
import com.naver.maps.map.util.MarkerIcons
import ted.gun0912.clustering.naver.TedNaverClustering
import java.io.Serializable

class NaverMapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var binding: FragmentNaverMapBinding
    private var myLocation: LatLng? = LatLng(37.5667, 126.9783)
    fun getMyLocation(location: Location) {
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

        binding.tabTouristSpot
        binding.tabParking


        return binding.root
    }


    private lateinit var markerToiletItem: ToiletItem
    private lateinit var markerTouristItem: TouristSpotItem
    private lateinit var markerParkingItem: ParkingItem

    override fun onMapReady(naverMap: NaverMap) {

        // 내 위치 마커
        val myMarker = Marker()
        //myMarker.position = myLocation!!

        // 임시 테스트 좌표
        myMarker.position = LatLng(33.426865, 126.505773)

        myMarker.icon = MarkerIcons.RED
        myMarker.map = naverMap

        val toiletInfoWindow = InfoWindow()
        val touristInfoWindow = InfoWindow()
        val parkingInfoWindow = InfoWindow()

        fun closeInfoWindow() {
            toiletInfoWindow.close()
            touristInfoWindow.close()
            parkingInfoWindow.close()
        }

        // 지도 클릭시 이벤트 처리 -> 열려있는 InfoWindow 전부 닫기
        naverMap.setOnMapClickListener { pointF, latLng ->
            closeInfoWindow()
        }

        naverMap.moveCamera(CameraUpdate.scrollTo(myMarker.position))

        // 내 위치 버튼 누를 때
        binding.ibMyLocation.setOnClickListener {
            (activity as MainActivity).requestMyLocation()
            naverMap.moveCamera(CameraUpdate.scrollTo(myMarker.position))
        }

        TedNaverClustering.with<ToiletItem>(requireContext(), naverMap)
            .items(totalItemsToilet)
            .customMarker {
                Marker().apply {
                    captionText = it.toiletNm
                    width = Marker.SIZE_AUTO
                    height = Marker.SIZE_AUTO
                    icon = MarkerIcons.BLACK
                    iconTintColor = Integer.parseInt("FFE37E", 16)
                }
            }.markerClickListener {
                closeInfoWindow()
                markerToiletItem = it
                toiletInfoWindow.apply {
                    position = LatLng(it.laCrdnt.toDouble(), it.loCrdnt.toDouble())
                    adapter = object : InfoWindow.DefaultTextAdapter(requireContext()) {
                        override fun getText(infoWindow: InfoWindow): CharSequence =
                            it.toiletNm
                    }
                    open(naverMap)
                }.setOnClickListener {
                    val intent = Intent(context, ToiletDetailActivity::class.java)
                    intent.putExtra("toiletItem", markerToiletItem as Serializable)
                    requireContext().startActivity(intent)
                    true
                }
            }
            .clusterAnimation(true)
            .minClusterSize(5)
            .make()

        TedNaverClustering.with<TouristSpotItem>(requireContext(), naverMap)
            .items(totalItemsTourist)
            .customMarker {
                Marker().apply {
                    captionText = it.title
                    width = Marker.SIZE_AUTO
                    height = Marker.SIZE_AUTO
                    icon = MarkerIcons.BLACK
                    iconTintColor = Integer.parseInt("96E389", 16)
                }
            }.markerClickListener {
                closeInfoWindow()
                markerTouristItem = it
                touristInfoWindow.apply {
                    position = LatLng(it.latitude.toDouble(), it.longitude.toDouble())
                    adapter = object : InfoWindow.DefaultTextAdapter(requireContext()) {
                        override fun getText(infoWindow: InfoWindow): CharSequence =
                            it.title
                    }
                    open(naverMap)
                }.onClickListener = OnClickListener {
                    val intent = Intent(context, TouristSpotDetailActivity::class.java)
                    intent.putExtra("touristItem", markerTouristItem as Serializable)
                    requireContext().startActivity(intent)
                    true
                }
            }
            .clusterAnimation(true)
            .minClusterSize(5)
            .make()

        TedNaverClustering.with<ParkingItem>(requireContext(), naverMap)
            .items(totalItemsParking)
            .customMarker {
                Marker().apply {
                    captionText = it.name
                    width = Marker.SIZE_AUTO
                    height = Marker.SIZE_AUTO
                    icon = MarkerIcons.BLACK
                    iconTintColor = Integer.parseInt("AECFFF", 16)
                }
            }.markerClickListener {
                closeInfoWindow()
                markerParkingItem = it
                parkingInfoWindow.apply {
                    position = LatLng(it.latitude.toDouble(), it.longitude.toDouble())
                    adapter = object : InfoWindow.DefaultTextAdapter(requireContext()) {
                        override fun getText(infoWindow: InfoWindow): CharSequence = it.name
                    }
                    open(naverMap)
                }.onClickListener = OnClickListener {
                    val intent = Intent(context, ParkingDetailActivity::class.java)
                    intent.putExtra("parkingItem", markerParkingItem as Parcelable)
                    requireContext().startActivity(intent)
                    true
                }
            }
            .clusterAnimation(true)
            .minClusterSize(5)
            .make()
    }
}