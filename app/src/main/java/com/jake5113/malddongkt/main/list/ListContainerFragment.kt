package com.jake5113.malddongkt.main.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.jake5113.malddongkt.databinding.FragmentListContainerBinding
import com.jake5113.malddongkt.main.list.parking.ParkingRecyclerAdapter
import com.jake5113.malddongkt.main.list.parking.ParkingItem
import com.jake5113.malddongkt.main.list.toilet.ToiletItem
import com.jake5113.malddongkt.main.list.toilet.ToiletRecyclerAdapter
import com.jake5113.malddongkt.main.list.touristspot.TouristSpotItem
import com.jake5113.malddongkt.main.list.touristspot.TouristSpotRecyclerAdapter

class ListContainerFragment : Fragment() {

    lateinit var binding: FragmentListContainerBinding

    val totalItemsToilet: MutableList<ToiletItem> = mutableListOf()
    val totalItemsTourist: MutableList<TouristSpotItem> = mutableListOf()
    val totalItemsParking: MutableList<ParkingItem> = mutableListOf()

    var categoryItemsToilet: MutableList<ToiletItem> = mutableListOf()
    var categoryItemsTourist: MutableList<TouristSpotItem> = mutableListOf()
    var categoryItemsParking: MutableList<ParkingItem> = mutableListOf()

    var spinnerItemsCategory = mutableListOf<String>()
    lateinit var adapter: ArrayAdapter<String>

    companion object { // Linear, Grid 클릭 시 값 저장
        const val RADIO_GRID = 2
        const val RADIO_LINEAR = 1
    }
    var recyclerState = RADIO_GRID

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListContainerBinding.inflate(inflater, container, false)
        // 스피너 설정
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinnerItemsCategory)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.onItemSelectedListener = itemSelectedListener

        binding.spinner.adapter = adapter

        binding.searchView.setOnQueryTextFocusChangeListener { v, hasFocus ->
            if(!hasFocus){
                binding.searchView.isIconified = true
            }
        }

        // 리사이클러 모양 타입 설정 리스너
        binding.rgRecyclerTypeSelection.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                binding.radiobtnGrid.id -> {
                    recyclerState = RADIO_GRID
                    if (binding.tabs.getTabAt(0)!!.isSelected) toiletGrid()
                    else if (binding.tabs.getTabAt(1)!!.isSelected) touristGrid()
                    else if (binding.tabs.getTabAt(2)!!.isSelected) parkingGrid()
                }

                binding.radiobtnLinear.id -> {
                    recyclerState = RADIO_LINEAR
                    if (binding.tabs.getTabAt(0)!!.isSelected) toiletLinear()
                    else if (binding.tabs.getTabAt(1)!!.isSelected) touristLinear()
                    else if (binding.tabs.getTabAt(2)!!.isSelected) parkingLinear()
                }
            }
        }


        binding.tabs.addOnTabSelectedListener(tabListner)
        binding.tabs.getTabAt(0)!!.select()


        toiletGrid()

        return binding.root
    }///////////////////////////////onCreateView()////////////////////////////////////////////

    val itemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            binding.spinner.setSelection(0)
        }
    }

    var tabListner = object : OnTabSelectedListener {

        override fun onTabSelected(tab: TabLayout.Tab?) {
            when (tab?.text) {
                "화장실" -> {
                    when (recyclerState) {
                        RADIO_GRID -> toiletGrid()
                        RADIO_LINEAR -> toiletLinear()
                    }
                }

                "관광지" -> {
                    when (recyclerState) {
                        RADIO_GRID -> touristGrid()
                        RADIO_LINEAR -> touristLinear()
                    }
                }

                "주차장" -> {
                    when (recyclerState) {
                        RADIO_GRID -> parkingGrid()
                        RADIO_LINEAR -> parkingLinear()
                    }
                }
            }
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
        }

        override fun onTabReselected(tab: TabLayout.Tab?) {
        }
    }


    // 어댑터 교체 함수
    fun toiletGrid() {
        binding.recycler.layoutManager = GridLayoutManager(context, 2)
        binding.recycler.adapter =
            ToiletRecyclerAdapter(requireContext(), totalItemsToilet, true)
    }

    fun toiletLinear() {
        binding.recycler.layoutManager = GridLayoutManager(context, 1)
        binding.recycler.adapter =
            ToiletRecyclerAdapter(requireContext(), totalItemsToilet, false)
    }

    fun touristGrid() {
        binding.recycler.layoutManager = GridLayoutManager(context, 2)
        binding.recycler.adapter =
            TouristSpotRecyclerAdapter(requireContext(), totalItemsTourist, true)
    }

    fun touristLinear() {
        binding.recycler.layoutManager = GridLayoutManager(context, 1)
        binding.recycler.adapter =
            TouristSpotRecyclerAdapter(requireContext(), totalItemsTourist, false)
    }

    fun parkingGrid() {
        binding.recycler.layoutManager = GridLayoutManager(context, 2)
        binding.recycler.adapter =
            ParkingRecyclerAdapter(requireContext(), totalItemsParking, true)
    }

    fun parkingLinear() {
        binding.recycler.layoutManager = GridLayoutManager(context, 1)
        binding.recycler.adapter =
            ParkingRecyclerAdapter(requireContext(), totalItemsParking, false)
    }

}