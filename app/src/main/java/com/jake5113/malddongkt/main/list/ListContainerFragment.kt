package com.jake5113.malddongkt.main.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.jake5113.malddongkt.databinding.FragmentListContainerBinding
import com.jake5113.malddongkt.main.MainActivity
import com.jake5113.malddongkt.main.list.parking.ParkingAdapter
import com.jake5113.malddongkt.main.list.parking.ParkingItem
import com.jake5113.malddongkt.main.list.toilet.ToiletItem
import com.jake5113.malddongkt.main.list.toilet.ToiletRecyclerAdapter
import com.jake5113.malddongkt.main.list.toilet.ToiletResponse
import com.jake5113.malddongkt.main.list.touristspot.TouristSpotItem
import com.jake5113.malddongkt.main.list.touristspot.TouristSpotRecyclerAdapter
import com.jake5113.malddongkt.network.RetrofitApiService
import com.jake5113.malddongkt.network.RetrofitHelper
import kotlinx.coroutines.awaitAll
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListContainerFragment : Fragment() {

    lateinit var binding: FragmentListContainerBinding
    var itemsTourist: MutableList<TouristSpotItem> = mutableListOf()
    var itemsToilet: MutableList<ToiletItem> = mutableListOf()
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

        // 임시데이터
        if (itemsTourist.size == 0) {
            itemsTourist.add(
                TouristSpotItem(
                    "관광지1",
                    "간단한 설명 1!",
                    "https://cdn.pixabay.com/photo/2023/03/27/14/18/british-shorthair-7880879_640.jpg"
                )
            )
            itemsTourist.add(
                TouristSpotItem(
                    "관광지2",
                    "간단한 설명 2!",
                    "https://cdn.pixabay.com/photo/2023/03/27/14/18/british-shorthair-7880879_640.jpg"
                )
            )
            itemsTourist.add(
                TouristSpotItem(
                    "관광지3",
                    "간단한 설명 3!",
                    "https://cdn.pixabay.com/photo/2023/03/27/14/18/british-shorthair-7880879_640.jpg"
                )
            )
            itemsTourist.add(
                TouristSpotItem(
                    "관광지4",
                    "간단한 설명 4!",
                    "https://cdn.pixabay.com/photo/2023/03/27/14/18/british-shorthair-7880879_640.jpg"
                )
            )
            itemsTourist.add(
                TouristSpotItem(
                    "관광지5",
                    "간단한 설명 5!",
                    "https://cdn.pixabay.com/photo/2023/03/27/14/18/british-shorthair-7880879_640.jpg"
                )
            )
            itemsTourist.add(
                TouristSpotItem(
                    "관광지6",
                    "간단한 설명 6!",
                    "https://cdn.pixabay.com/photo/2023/03/27/14/18/british-shorthair-7880879_640.jpg"
                )
            )
            itemsTourist.add(
                TouristSpotItem(
                    "관광지7",
                    "간단한 설명 7!",
                    "https://cdn.pixabay.com/photo/2023/03/27/14/18/british-shorthair-7880879_640.jpg"
                )
            )
            itemsTourist.add(
                TouristSpotItem(
                    "관광지8",
                    "간단한 설명 8!",
                    "https://cdn.pixabay.com/photo/2023/03/27/14/18/british-shorthair-7880879_640.jpg"
                )
            )
            itemsTourist.add(
                TouristSpotItem(
                    "관광지9",
                    "간단한 설명 9!",
                    "https://cdn.pixabay.com/photo/2023/03/27/14/18/british-shorthair-7880879_640.jpg"
                )
            )
            itemsTourist.add(
                TouristSpotItem(
                    "관광지10",
                    "간단한 설명10!",
                    "https://cdn.pixabay.com/photo/2023/03/27/14/18/british-shorthair-7880879_640.jpg"
                )
            )
            itemsTourist.add(
                TouristSpotItem(
                    "관광지11",
                    "간단한 설명11!",
                    "https://cdn.pixabay.com/photo/2023/03/27/14/18/british-shorthair-7880879_640.jpg"
                )
            )
        }
        if (itemsParking.size == 0) {
            itemsParking.add(ParkingItem("주차장1", "주소1"))
            itemsParking.add(ParkingItem("주차장2", "주소2"))
            itemsParking.add(ParkingItem("주차장3", "주소3"))
            itemsParking.add(ParkingItem("주차장4", "주소4"))
            itemsParking.add(ParkingItem("주차장5", "주소5"))
            itemsParking.add(ParkingItem("주차장6", "주소6"))
            itemsParking.add(ParkingItem("주차장7", "주소7"))
            itemsParking.add(ParkingItem("주차장8", "주소8"))
            itemsParking.add(ParkingItem("주차장9", "주소9"))
            itemsParking.add(ParkingItem("주차장10", "주소10"))
            itemsParking.add(ParkingItem("주차장11", "주소11"))
            itemsParking.add(ParkingItem("주차장12", "주소12"))
            itemsParking.add(ParkingItem("주차장13", "주소13"))
        }

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
            ParkingAdapter(requireContext(), itemsParking, true)
    }

    fun parkingLinear() {
        binding.recycler.layoutManager = GridLayoutManager(context, 1)
        binding.recycler.adapter =
            ParkingAdapter(requireContext(), itemsParking, false)
    }

}