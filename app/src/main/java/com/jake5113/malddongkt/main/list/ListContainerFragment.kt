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
import com.google.android.material.tabs.TabLayout.GONE
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.VISIBLE
import com.jake5113.malddongkt.R
import com.jake5113.malddongkt.databinding.FragmentListContainerBinding
import com.jake5113.malddongkt.main.MainActivity
import com.jake5113.malddongkt.main.list.parking.ParkingRecyclerAdapter
import com.jake5113.malddongkt.main.list.parking.ParkingItem
import com.jake5113.malddongkt.main.list.toilet.ToiletItem
import com.jake5113.malddongkt.main.list.toilet.ToiletRecyclerAdapter
import com.jake5113.malddongkt.main.list.touristspot.TouristSpotItem
import com.jake5113.malddongkt.main.list.touristspot.TouristSpotRecyclerAdapter

class ListContainerFragment : Fragment() {

    lateinit var binding: FragmentListContainerBinding

    // 장소 전체 리스트
    val totalItemsToilet: MutableList<ToiletItem> = mutableListOf()
    val totalItemsTourist: MutableList<TouristSpotItem> = mutableListOf()
    val totalItemsParking: MutableList<ParkingItem> = mutableListOf()

    // 지역별 정렬시 보여줄 리스트
    var categoryItemsToilet: MutableList<ToiletItem> = mutableListOf()
    var categoryItemsTourist: MutableList<TouristSpotItem> = mutableListOf()
    var categoryItemsParking: MutableList<ParkingItem> = mutableListOf()

    // 스피너에 보여줄 지역 리스트
    var spinnerItemsCategory = mutableListOf<String>()
    lateinit var spinnerAdapter: ArrayAdapter<String>

    companion object { // Linear, Grid 클릭 시 값 저장
        const val RADIO_GRID = 2
        const val RADIO_LINEAR = 1
    }

    // 초기값 설정.
    private var recyclerState = RADIO_GRID
    private var recyclerTypeCheckedId = 0 // 임시 데이터

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListContainerBinding.inflate(inflater, container, false)

        // 초기화
        recyclerTypeCheckedId = binding.radiobtnGrid.id

        // 스피너 설정
        spinnerAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, spinnerItemsCategory)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinner.onItemSelectedListener = itemSelectedListener
        binding.spinner.adapter = spinnerAdapter

        binding.spinner.dropDownVerticalOffset = 230


        // TODO 검색창에 포커스 돼있지 않으면 닫기.
        /*binding.searchView.setOnQueryTextFocusChangeListener { v, hasFocus ->
            if (!hasFocus) binding.searchView.isIconified = true
        }*/

        // 리사이클러 모양 타입 설정 리스너
        binding.rgRecyclerTypeSelection.setOnCheckedChangeListener { group, checkedId ->
            recyclerTypeCheckedId = checkedId
            checkStateAndType()
        }

        binding.tabs.addOnTabSelectedListener(tabListener)
        binding.tabs.getTabAt(0)!!.select()

        // swipe refresh
        binding.swipe.setOnRefreshListener {
            // 인터넷 접속
            (activity as MainActivity).getToiletItems()
            (activity as MainActivity).getTouristSpotItems()
            (activity as MainActivity).getParkingItems()

            checkStateAndType()
            binding.swipe.isRefreshing = false
        }
        return binding.root
    }///////////////////////////////onCreateView()////////////////////////////////////////////

    // spinner item 리스너
    private val itemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            val selectedText = parent?.getItemAtPosition(position).toString()

            // 카테고리별 리스트 비우기
            categoryItemsToilet = mutableListOf()
            categoryItemsTourist = mutableListOf()
            categoryItemsParking = mutableListOf()

            if (selectedText == "전체") {
                categoryItemsToilet = totalItemsToilet
                categoryItemsTourist = totalItemsTourist
                categoryItemsParking = totalItemsParking
            } else {
                // 화장실 - 지역 카테고리에 맞는 데이터 목록 추가
                totalItemsToilet.forEach {
                    if (it.emdNm == selectedText) categoryItemsToilet.add(it)
                }

                // 관광지 - 지역 카테고리에 맞는 데이터 목록 추가
                totalItemsTourist.forEach {
                    if (it.address.contains(selectedText)) categoryItemsTourist.add(it)
                }
                // 주차장 - 지역 카테고리에 맞는 데이터 목록 추가
                totalItemsParking.forEach {
                    if (it.lnmAdres.contains(selectedText)) categoryItemsParking.add(it)
                }
            }
            checkStateAndType()
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            binding.spinner.setSelection(0)
            checkStateAndType()
        }
    }

    private var tabListener = object : OnTabSelectedListener {

        override fun onTabSelected(tab: TabLayout.Tab?) {
            checkStateAndType()
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
        }

        override fun onTabReselected(tab: TabLayout.Tab?) {
        }
    }

    // 탭 상태, 뷰 타입 확인 후 조건에 맞는 어댑터 설정 함수
    fun checkStateAndType() {
        binding.ivInfoEmpty.visibility = GONE
        when (recyclerTypeCheckedId) {
            binding.radiobtnGrid.id -> {
                recyclerState = RADIO_GRID
                when (binding.tabs.selectedTabPosition) {
                    0 -> {
                        toiletGrid()
                        if (categoryItemsToilet.isEmpty()) binding.ivInfoEmpty.visibility = VISIBLE
                    }

                    1 -> {
                        touristGrid()
                        if (categoryItemsTourist.isEmpty()) binding.ivInfoEmpty.visibility = VISIBLE
                    }

                    2 -> {
                        parkingGrid()
                        if (categoryItemsParking.isEmpty()) binding.ivInfoEmpty.visibility = VISIBLE
                    }
                }
            }

            binding.radiobtnLinear.id -> {
                recyclerState = RADIO_LINEAR
                when (binding.tabs.selectedTabPosition) {
                    0 -> {
                        toiletLinear()
                        if (categoryItemsToilet.isEmpty()) binding.ivInfoEmpty.visibility = VISIBLE
                    }

                    1 -> {
                        touristLinear()
                        if (categoryItemsTourist.isEmpty()) binding.ivInfoEmpty.visibility = VISIBLE
                    }

                    2 -> {
                        parkingLinear()
                        if (categoryItemsParking.isEmpty()) binding.ivInfoEmpty.visibility = VISIBLE
                    }
                }
            }
        }
    }

    // 어댑터 교체 함수
    private fun toiletGrid() {
        binding.recycler.layoutManager = GridLayoutManager(context, 2)
        binding.recycler.adapter =
            ToiletRecyclerAdapter(requireContext(), categoryItemsToilet, true)
    }

    private fun toiletLinear() {
        binding.recycler.layoutManager = GridLayoutManager(context, 1)
        binding.recycler.adapter =
            ToiletRecyclerAdapter(requireContext(), categoryItemsToilet, false)
    }

    private fun touristGrid() {
        binding.recycler.layoutManager = GridLayoutManager(context, 2)
        binding.recycler.adapter =
            TouristSpotRecyclerAdapter(requireContext(), categoryItemsTourist, true)
    }

    private fun touristLinear() {
        binding.recycler.layoutManager = GridLayoutManager(context, 1)
        binding.recycler.adapter =
            TouristSpotRecyclerAdapter(requireContext(), categoryItemsTourist, false)
    }

    private fun parkingGrid() {
        binding.recycler.layoutManager = GridLayoutManager(context, 2)
        binding.recycler.adapter =
            ParkingRecyclerAdapter(requireContext(), categoryItemsParking, true)
    }

    private fun parkingLinear() {
        binding.recycler.layoutManager = GridLayoutManager(context, 1)
        binding.recycler.adapter =
            ParkingRecyclerAdapter(requireContext(), categoryItemsParking, false)
    }

}