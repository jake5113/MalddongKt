package com.jake5113.malddongkt.main.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
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
    var itemsToilet: MutableList<ToiletItem> = mutableListOf()
    var itemsTourist: MutableList<TouristSpotItem> = mutableListOf()
    var itemsParking: MutableList<ParkingItem> = mutableListOf()

    companion object {
        const val RADIO_GRID = 2
        const val RADIO_LINEAR = 1
    }

    var radioState = RADIO_GRID

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListContainerBinding.inflate(inflater, container, false)

        binding.radiobtns.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                binding.radiobtnGrid.id -> {
                    radioState = RADIO_GRID
                    if (binding.tabs.getTabAt(0)!!.isSelected) toiletGrid()
                    else if (binding.tabs.getTabAt(1)!!.isSelected) touristGrid()
                    else if (binding.tabs.getTabAt(2)!!.isSelected) parkingGrid()
                }

                binding.radiobtnLinear.id -> {
                    radioState = RADIO_LINEAR
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
    }////////////////////////////////////////////////////////////////////////////////////////////

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    var tabListner = object : OnTabSelectedListener {

        override fun onTabSelected(tab: TabLayout.Tab?) {
            when (tab?.text) {
                "화장실" -> {
                    when (radioState) {
                        RADIO_GRID -> toiletGrid()
                        RADIO_LINEAR -> toiletLinear()
                    }
                }

                "관광지" -> {
                    when (radioState) {
                        RADIO_GRID -> touristGrid()
                        RADIO_LINEAR -> touristLinear()
                    }
                }

                "주차장" -> {
                    when (radioState) {
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
            ToiletRecyclerAdapter(requireContext(), itemsToilet, true)
    }

    fun toiletLinear() {
        binding.recycler.layoutManager = GridLayoutManager(context, 1)
        binding.recycler.adapter =
            ToiletRecyclerAdapter(requireContext(), itemsToilet, false)
    }

    fun touristGrid() {
        binding.recycler.layoutManager = GridLayoutManager(context, 2)
        binding.recycler.adapter =
            TouristSpotRecyclerAdapter(requireContext(), itemsTourist, true)
    }

    fun touristLinear() {
        binding.recycler.layoutManager = GridLayoutManager(context, 1)
        binding.recycler.adapter =
            TouristSpotRecyclerAdapter(requireContext(), itemsTourist, false)
    }

    fun parkingGrid(){
        binding.recycler.layoutManager = GridLayoutManager(context, 2)
        binding.recycler.adapter =
            ParkingRecyclerAdapter(requireContext(), itemsParking, true)
    }

    fun parkingLinear() {
        binding.recycler.layoutManager = GridLayoutManager(context, 1)
        binding.recycler.adapter =
            ParkingRecyclerAdapter(requireContext(), itemsParking, false)
    }

}