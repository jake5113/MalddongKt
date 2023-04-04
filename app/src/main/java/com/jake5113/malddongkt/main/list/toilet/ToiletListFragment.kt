package com.jake5113.malddongkt.main.list.toilet

import android.graphics.drawable.GradientDrawable.Orientation
import android.icu.lang.UCharacter.VerticalOrientation
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jake5113.malddongkt.databinding.FragmentToiletListBinding

class ToiletListFragment : Fragment() {
    lateinit var binding: FragmentToiletListBinding
    var items: MutableList<ToiletItem> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentToiletListBinding.inflate(inflater, container, false)

        // 처음에만 리스트에 추가
        if(items.size == 0){
        items.add(ToiletItem("주소1","이름1", mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg")))
        items.add(ToiletItem("주소2","이름2", mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg")))
        items.add(ToiletItem("주소3","이름3", mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg")))
        items.add(ToiletItem("주소4","이름4", mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg")))
        items.add(ToiletItem("주소5","이름5", mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg")))
        items.add(ToiletItem("주소6","이름6", mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg")))
        items.add(ToiletItem("주소7","이름7", mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg")))
        items.add(ToiletItem("주소8","이름8", mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg")))
        items.add(ToiletItem("주소9","이름9", mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg")))
        items.add(ToiletItem("주소10","이름10", mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg")))
        items.add(ToiletItem("주소11","이름11", mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg")))
        items.add(ToiletItem("주소12","이름12", mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg")))
        items.add(ToiletItem("주소13","이름13", mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg")))
        }

        binding.toolbarToilet.radiobtnGrid.setOnClickListener {
            binding.recycler.layoutManager = GridLayoutManager(context, 2)
            binding.recycler.adapter = ToiletShortRecyclerAdapter(requireContext(), items)
        }

        binding.toolbarToilet.radiobtnList.setOnClickListener {
            binding.recycler.layoutManager = GridLayoutManager(context, 1)
            binding.recycler.adapter = ToiletLongRecyclerAdapter(requireContext(), items)
        }
        binding.recycler.adapter = ToiletShortRecyclerAdapter(requireContext(), items)

        return binding.root
    }
}