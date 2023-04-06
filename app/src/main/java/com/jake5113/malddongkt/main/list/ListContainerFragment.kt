package com.jake5113.malddongkt.main.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.jake5113.malddongkt.R
import com.jake5113.malddongkt.databinding.FragmentListContainerBinding
import com.jake5113.malddongkt.main.list.parking.ParkingListFragment
import com.jake5113.malddongkt.main.list.toilet.ToiletListFragment
import com.jake5113.malddongkt.main.list.toilet.ToiletRecyclerAdapter
import com.jake5113.malddongkt.main.list.touristspot.TouristSpotListFragment

class ListContainerFragment : Fragment() {

    lateinit var binding: FragmentListContainerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListContainerBinding.inflate(inflater, container, false)

        childFragmentManager.beginTransaction().replace(R.id.fragments_container, ToiletListFragment()).commit()

        binding.tabs.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val transaction = childFragmentManager.beginTransaction()
                when (tab?.position) {
                    0 -> transaction.replace(R.id.fragments_container, ToiletListFragment()).commit()
                    1 -> transaction.replace(R.id.fragments_container, TouristSpotListFragment()).commit()
                    2 -> transaction.replace(R.id.fragments_container, ParkingListFragment()).commit()
                    else -> transaction.replace(R.id.fragments_container, ToiletListFragment()).commit()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        return binding.root
    }
}