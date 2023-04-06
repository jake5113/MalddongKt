package com.jake5113.malddongkt.main.list.parking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.jake5113.malddongkt.R
import com.jake5113.malddongkt.databinding.FragmentParkingListBinding
import com.jake5113.malddongkt.databinding.FragmentToiletListBinding
import com.jake5113.malddongkt.main.list.touristspot.TouristSpotListFragment

class ParkingListFragment : Fragment() {
    private lateinit var binding: FragmentParkingListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentParkingListBinding.inflate(inflater, container, false)


        return binding.root
    }
}