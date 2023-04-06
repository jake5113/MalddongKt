package com.jake5113.malddongkt.main.list.touristspot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.jake5113.malddongkt.R
import com.jake5113.malddongkt.databinding.FragmentTouristSpotListBinding
import com.jake5113.malddongkt.main.list.toilet.ToiletRecyclerAdapter

class TouristSpotListFragment : Fragment() {

    lateinit var binding:FragmentTouristSpotListBinding
    var items : MutableList<TouristSpotItem> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTouristSpotListBinding.inflate(inflater, container, false)

        // 처음에만 리스트에 추가
        if(items.size == 0){
            items.add(TouristSpotItem("관광지1", "간단한 설명 1!", "https://cdn.pixabay.com/photo/2023/03/27/14/18/british-shorthair-7880879_640.jpg"))
            items.add(TouristSpotItem("관광지2", "간단한 설명 2!", "https://cdn.pixabay.com/photo/2023/03/27/14/18/british-shorthair-7880879_640.jpg"))
            items.add(TouristSpotItem("관광지3", "간단한 설명 3!", "https://cdn.pixabay.com/photo/2023/03/27/14/18/british-shorthair-7880879_640.jpg"))
            items.add(TouristSpotItem("관광지4", "간단한 설명 4!", "https://cdn.pixabay.com/photo/2023/03/27/14/18/british-shorthair-7880879_640.jpg"))
            items.add(TouristSpotItem("관광지5", "간단한 설명 5!", "https://cdn.pixabay.com/photo/2023/03/27/14/18/british-shorthair-7880879_640.jpg"))
            items.add(TouristSpotItem("관광지6", "간단한 설명 6!", "https://cdn.pixabay.com/photo/2023/03/27/14/18/british-shorthair-7880879_640.jpg"))
            items.add(TouristSpotItem("관광지7", "간단한 설명 7!", "https://cdn.pixabay.com/photo/2023/03/27/14/18/british-shorthair-7880879_640.jpg"))
            items.add(TouristSpotItem("관광지8", "간단한 설명 8!", "https://cdn.pixabay.com/photo/2023/03/27/14/18/british-shorthair-7880879_640.jpg"))
            items.add(TouristSpotItem("관광지9", "간단한 설명 9!", "https://cdn.pixabay.com/photo/2023/03/27/14/18/british-shorthair-7880879_640.jpg"))
            items.add(TouristSpotItem("관광지10", "간단한 설명10!", "https://cdn.pixabay.com/photo/2023/03/27/14/18/british-shorthair-7880879_640.jpg"))
            items.add(TouristSpotItem("관광지11", "간단한 설명11!", "https://cdn.pixabay.com/photo/2023/03/27/14/18/british-shorthair-7880879_640.jpg"))
        }

/*        binding.toolbarTouistSpot.radiobtnGrid.setOnClickListener {
            binding.recycler.layoutManager = GridLayoutManager(context, 2)
            binding.recycler.adapter = TouristSpotRecyclerAdapter(requireContext(), items, true)
        }

        binding.toolbarTouistSpot.radiobtnList.setOnClickListener {
            binding.recycler.layoutManager = GridLayoutManager(context, 1)
            binding.recycler.adapter = TouristSpotRecyclerAdapter(requireContext(), items, false)
        }*/
        binding.recycler.adapter = TouristSpotRecyclerAdapter(requireContext(), items, true)

        return binding.root
    }
}