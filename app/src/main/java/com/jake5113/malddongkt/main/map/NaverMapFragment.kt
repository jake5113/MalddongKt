package com.jake5113.malddongkt.main.map

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jake5113.malddongkt.R
import com.jake5113.malddongkt.databinding.FragmentNaverMapBinding
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback

class NaverMapFragment : Fragment(), OnMapReadyCallback {
    lateinit var binding: FragmentNaverMapBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNaverMapBinding.inflate(inflater, container, false)

        val fm = childFragmentManager
        val mapFragment = fm.findFragmentById(R.id.navermap_container) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.navermap_container, it).commit()
            }

        mapFragment.getMapAsync(this)

        return binding.root
    }

    override fun onMapReady(naverMap: NaverMap) {

    }
}