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
    var recyclerTypecheckedId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListContainerBinding.inflate(inflater, container, false)

        // 초기화
        recyclerTypecheckedId = binding.radiobtnGrid.id

        // 카테고리 전체에 해당하는 리스트를 추가하여 초기화
        categoryItemsToilet = totalItemsToilet
        categoryItemsTourist = totalItemsTourist
        categoryItemsParking = totalItemsParking

        // 스피너 설정
        adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            spinnerItemsCategory
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //binding.spinner.onItemSelectedListener = itemSelectedListener

        binding.spinner.adapter = adapter

        // 검색창에 포커스 돼있지 않으면 닫기.
        binding.searchView.setOnQueryTextFocusChangeListener { v, hasFocus ->
            if (!hasFocus) binding.searchView.isIconified = true
        }

        // 리사이클러 모양 타입 설정 리스너
        binding.rgRecyclerTypeSelection.setOnCheckedChangeListener { group, checkedId ->
            recyclerTypecheckedId = checkedId
            checkStateAndType()
        }

        binding.tabs.addOnTabSelectedListener(tabListner)
        binding.tabs.getTabAt(0)!!.select()

        toiletGrid()

        return binding.root
    }///////////////////////////////onCreateView()////////////////////////////////////////////

    private val itemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            val selectedText = parent?.getItemAtPosition(position).toString()

            binding.ivInfoEmpty.visibility = GONE

            if (selectedText == "전체") {
                categoryItemsToilet = totalItemsToilet
                categoryItemsTourist = totalItemsTourist
                categoryItemsParking = totalItemsParking
                return
            } else {
                // 카테고리별 리스트 비우기
                categoryItemsToilet = mutableListOf()
                categoryItemsTourist = mutableListOf()
                categoryItemsParking = mutableListOf()

                // 화장실 - 지역 카테고리에 맞는 데이터 목록 추가
                for (i in 0 until totalItemsToilet.size)
                    if (totalItemsToilet[i].emdNm == selectedText) categoryItemsToilet.add(
                        totalItemsToilet[i]
                    )

                // 관광지 - 지역 카테고리에 맞는 데이터 목록 추가
                for (i in 0 until totalItemsTourist.size)
                    if (totalItemsTourist[i].address.contains(selectedText)) categoryItemsTourist.add(
                        totalItemsTourist[i]
                    )

                // 주차장 - 지역 카테고리에 맞는 데이터 목록 추가
                for (i in 0 until totalItemsParking.size)
                    if (totalItemsParking[i].lnmAdres.contains(selectedText)) categoryItemsParking.add(
                        totalItemsParking[i]
                    )
            }

            if (categoryItemsToilet.isNotEmpty())
                binding.recycler.adapter =
                    ToiletRecyclerAdapter(requireContext(), categoryItemsToilet, true)
            else binding.ivInfoEmpty.visibility = VISIBLE


            if (categoryItemsTourist.isNotEmpty())
                binding.recycler.adapter =
                    TouristSpotRecyclerAdapter(requireContext(), categoryItemsTourist, true)
            else binding.ivInfoEmpty.visibility = VISIBLE


            if (categoryItemsParking.isNotEmpty())
                binding.recycler.adapter =
                    ParkingRecyclerAdapter(requireContext(), categoryItemsParking, true)
            else binding.ivInfoEmpty.visibility = VISIBLE
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            binding.spinner.setSelection(0)
        }
    }


    private var tabListner = object : OnTabSelectedListener {

        override fun onTabSelected(tab: TabLayout.Tab?) {
            checkStateAndType()
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
        }

        override fun onTabReselected(tab: TabLayout.Tab?) {
        }
    }

    // 탭 상태, 뷰 타입 확인 후 조건에 맞는 데이터를 어댑터에 적용하는 함수
    fun checkStateAndType() {
        when (recyclerTypecheckedId) {
            binding.radiobtnGrid.id -> {
                recyclerState = RADIO_GRID
                when (binding.tabs.selectedTabPosition) {
                    0 -> toiletGrid()
                    1 -> touristGrid()
                    2 -> parkingGrid()
                }
            }

            binding.radiobtnLinear.id -> {
                recyclerState = RADIO_LINEAR
                when (binding.tabs.selectedTabPosition) {
                    0 -> toiletLinear()
                    1 -> touristLinear()
                    2 -> parkingLinear()
                }
            }
        }
    }

    // 어댑터 교체 함수
    fun toiletGrid() {
        binding.recycler.layoutManager = GridLayoutManager(context, 2)
        binding.recycler.adapter =
            ToiletRecyclerAdapter(requireContext(), categoryItemsToilet, true)
    }

    fun toiletLinear() {
        binding.recycler.layoutManager = GridLayoutManager(context, 1)
        binding.recycler.adapter =
            ToiletRecyclerAdapter(requireContext(), categoryItemsToilet, false)
    }

    fun touristGrid() {
        binding.recycler.layoutManager = GridLayoutManager(context, 2)
        binding.recycler.adapter =
            TouristSpotRecyclerAdapter(requireContext(), categoryItemsTourist, true)
    }

    fun touristLinear() {
        binding.recycler.layoutManager = GridLayoutManager(context, 1)
        binding.recycler.adapter =
            TouristSpotRecyclerAdapter(requireContext(), categoryItemsTourist, false)
    }

    fun parkingGrid() {
        binding.recycler.layoutManager = GridLayoutManager(context, 2)
        binding.recycler.adapter =
            ParkingRecyclerAdapter(requireContext(), categoryItemsParking, true)
    }

    fun parkingLinear() {
        binding.recycler.layoutManager = GridLayoutManager(context, 1)
        binding.recycler.adapter =
            ParkingRecyclerAdapter(requireContext(), categoryItemsParking, false)
    }

}