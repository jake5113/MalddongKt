package com.jake5113.malddongkt.main.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.jake5113.malddongkt.databinding.FragmentListContainerBinding
import com.jake5113.malddongkt.main.list.parking.ParkingAdapter
import com.jake5113.malddongkt.main.list.parking.ParkingItem
import com.jake5113.malddongkt.main.list.toilet.ToiletItem
import com.jake5113.malddongkt.main.list.toilet.ToiletRecyclerAdapter
import com.jake5113.malddongkt.main.list.touristspot.TouristSpotItem
import com.jake5113.malddongkt.main.list.touristspot.TouristSpotRecyclerAdapter

class ListContainerFragment : Fragment() {

    lateinit var binding: FragmentListContainerBinding
    var items_tourist: MutableList<TouristSpotItem> = mutableListOf()
    var items_toilet: MutableList<ToiletItem> = mutableListOf()
    var items_parking: MutableList<ParkingItem> = mutableListOf()

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

        // 임시데이터
        if (items_toilet.size == 0) {
            items_toilet.add(
                ToiletItem(
                    "주소1",
                    "이름1",
                    mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg")
                )
            )
            items_toilet.add(
                ToiletItem(
                    "주소2",
                    "이름2",
                    mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg")
                )
            )
            items_toilet.add(
                ToiletItem(
                    "주소3",
                    "이름3",
                    mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg")
                )
            )
            items_toilet.add(
                ToiletItem(
                    "주소4",
                    "이름4",
                    mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg")
                )
            )
            items_toilet.add(
                ToiletItem(
                    "주소5",
                    "이름5",
                    mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg")
                )
            )
            items_toilet.add(
                ToiletItem(
                    "주소6",
                    "이름6",
                    mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg")
                )
            )
            items_toilet.add(
                ToiletItem(
                    "주소7",
                    "이름7",
                    mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg")
                )
            )
            items_toilet.add(
                ToiletItem(
                    "주소8",
                    "이름8",
                    mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg")
                )
            )
            items_toilet.add(
                ToiletItem(
                    "주소9",
                    "이름9",
                    mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg")
                )
            )
            items_toilet.add(
                ToiletItem(
                    "주소10",
                    "이름10",
                    mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg")
                )
            )
            items_toilet.add(
                ToiletItem(
                    "주소11",
                    "이름11",
                    mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg")
                )
            )
            items_toilet.add(
                ToiletItem(
                    "주소12",
                    "이름12",
                    mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg")
                )
            )
            items_toilet.add(
                ToiletItem(
                    "주소13",
                    "이름13",
                    mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg")
                )
            )
        }
        if (items_tourist.size == 0) {
            items_tourist.add(
                TouristSpotItem(
                    "관광지1",
                    "간단한 설명 1!",
                    "https://cdn.pixabay.com/photo/2023/03/27/14/18/british-shorthair-7880879_640.jpg"
                )
            )
            items_tourist.add(
                TouristSpotItem(
                    "관광지2",
                    "간단한 설명 2!",
                    "https://cdn.pixabay.com/photo/2023/03/27/14/18/british-shorthair-7880879_640.jpg"
                )
            )
            items_tourist.add(
                TouristSpotItem(
                    "관광지3",
                    "간단한 설명 3!",
                    "https://cdn.pixabay.com/photo/2023/03/27/14/18/british-shorthair-7880879_640.jpg"
                )
            )
            items_tourist.add(
                TouristSpotItem(
                    "관광지4",
                    "간단한 설명 4!",
                    "https://cdn.pixabay.com/photo/2023/03/27/14/18/british-shorthair-7880879_640.jpg"
                )
            )
            items_tourist.add(
                TouristSpotItem(
                    "관광지5",
                    "간단한 설명 5!",
                    "https://cdn.pixabay.com/photo/2023/03/27/14/18/british-shorthair-7880879_640.jpg"
                )
            )
            items_tourist.add(
                TouristSpotItem(
                    "관광지6",
                    "간단한 설명 6!",
                    "https://cdn.pixabay.com/photo/2023/03/27/14/18/british-shorthair-7880879_640.jpg"
                )
            )
            items_tourist.add(
                TouristSpotItem(
                    "관광지7",
                    "간단한 설명 7!",
                    "https://cdn.pixabay.com/photo/2023/03/27/14/18/british-shorthair-7880879_640.jpg"
                )
            )
            items_tourist.add(
                TouristSpotItem(
                    "관광지8",
                    "간단한 설명 8!",
                    "https://cdn.pixabay.com/photo/2023/03/27/14/18/british-shorthair-7880879_640.jpg"
                )
            )
            items_tourist.add(
                TouristSpotItem(
                    "관광지9",
                    "간단한 설명 9!",
                    "https://cdn.pixabay.com/photo/2023/03/27/14/18/british-shorthair-7880879_640.jpg"
                )
            )
            items_tourist.add(
                TouristSpotItem(
                    "관광지10",
                    "간단한 설명10!",
                    "https://cdn.pixabay.com/photo/2023/03/27/14/18/british-shorthair-7880879_640.jpg"
                )
            )
            items_tourist.add(
                TouristSpotItem(
                    "관광지11",
                    "간단한 설명11!",
                    "https://cdn.pixabay.com/photo/2023/03/27/14/18/british-shorthair-7880879_640.jpg"
                )
            )
        }
        if (items_parking.size == 0) {
            items_parking.add(ParkingItem("주차장1", "주소1"))
            items_parking.add(ParkingItem("주차장2", "주소2"))
            items_parking.add(ParkingItem("주차장3", "주소3"))
            items_parking.add(ParkingItem("주차장4", "주소4"))
            items_parking.add(ParkingItem("주차장5", "주소5"))
            items_parking.add(ParkingItem("주차장6", "주소6"))
            items_parking.add(ParkingItem("주차장7", "주소7"))
            items_parking.add(ParkingItem("주차장8", "주소8"))
            items_parking.add(ParkingItem("주차장9", "주소9"))
            items_parking.add(ParkingItem("주차장10", "주소10"))
            items_parking.add(ParkingItem("주차장11", "주소11"))
            items_parking.add(ParkingItem("주차장12", "주소12"))
            items_parking.add(ParkingItem("주차장13", "주소13"))
        }

        binding.radiobtns.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                binding.radiobtnGrid.id -> {
                    radioState = RADIO_GRID
                    if (binding.tabs[0].isSelected) toiletGrid()
                    else if (binding.tabs[1].isSelected) touristGrid()
                    else if (binding.tabs[2].isSelected) parkingGrid()
                }

                binding.radiobtnLinear.id -> {
                    radioState = RADIO_LINEAR
                    if (binding.tabs[0].isSelected) toiletLinear()
                    else if (binding.tabs[1].isSelected) touristLinear()
                    else if (binding.tabs[2].isSelected) parkingLinear()
                }
            }
        }

        binding.radiobtns[0].isSelected = true
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
                else -> {
                    when (radioState) {
                        RADIO_GRID -> toiletGrid()
                        RADIO_LINEAR -> toiletLinear()
                    }
                }
            }
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
        }

        override fun onTabReselected(tab: TabLayout.Tab?) {
        }
    }

    fun toiletGrid() {
        binding.recycler.layoutManager = GridLayoutManager(context, 2)
        binding.recycler.adapter =
            ToiletRecyclerAdapter(requireContext(), items_toilet, true)
    }

    fun toiletLinear() {
        binding.recycler.layoutManager = GridLayoutManager(context, 1)
        binding.recycler.adapter =
            ToiletRecyclerAdapter(requireContext(), items_toilet, false)
    }

    fun touristGrid() {
        binding.recycler.layoutManager = GridLayoutManager(context, 2)
        binding.recycler.adapter =
            TouristSpotRecyclerAdapter(requireContext(), items_tourist, true)
    }

    fun touristLinear() {
        binding.recycler.layoutManager = GridLayoutManager(context, 1)
        binding.recycler.adapter =
            TouristSpotRecyclerAdapter(requireContext(), items_tourist, false)
    }

    fun parkingGrid(){
        binding.recycler.layoutManager = GridLayoutManager(context, 2)
        binding.recycler.adapter =
            ParkingAdapter(requireContext(), items_parking, true)
    }

    fun parkingLinear() {
        binding.recycler.layoutManager = GridLayoutManager(context, 1)
        binding.recycler.adapter =
            ParkingAdapter(requireContext(), items_parking, false)
    }

}