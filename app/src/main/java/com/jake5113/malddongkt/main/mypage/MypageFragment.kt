package com.jake5113.malddongkt.main.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jake5113.malddongkt.R
import com.jake5113.malddongkt.databinding.FragmentMypageBinding
import com.jake5113.malddongkt.main.MainActivity
import com.jake5113.malddongkt.main.list.ListContainerFragment
import com.jake5113.malddongkt.main.list.toilet.ToiletItem
import com.jake5113.malddongkt.main.list.toilet.ToiletRecyclerAdapter
import com.jake5113.malddongkt.main.list.touristspot.TouristSpotItem
import com.jake5113.malddongkt.main.list.touristspot.TouristSpotRecyclerAdapter

class MypageFragment : Fragment() {

    lateinit var binding :FragmentMypageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageBinding.inflate(inflater, container, false)



        return binding.root
    }
}