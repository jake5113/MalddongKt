package com.jake5113.malddongkt.main.toilet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        items.add(ToiletItem("주소", mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg"), "이름"))
        items.add(ToiletItem("주소1", mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg"), "ㅋㅋ"))
        items.add(ToiletItem("주소2", mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg"), "ㅇㅇ"))
        items.add(ToiletItem("주소3", mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg"), "ㄴㄴ"))
        items.add(ToiletItem("주소4", mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg"), "ㄹㄹ"))
        items.add(ToiletItem("주소5", mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg"), "ㅎㅎ"))
        items.add(ToiletItem("주소6", mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg"), "ㄷㄷ"))
        items.add(ToiletItem("주소", mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg"), "이름"))
        items.add(ToiletItem("주소1", mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg"), "ㅋㅋ"))
        items.add(ToiletItem("주소2", mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg"), "ㅇㅇ"))
        items.add(ToiletItem("주소3", mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg"), "ㄴㄴ"))
        items.add(ToiletItem("주소4", mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg"), "ㄹㄹ"))
        items.add(ToiletItem("주소5", mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg"), "ㅎㅎ"))
        items.add(ToiletItem("주소6", mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg"), "ㄷㄷ"))
        items.add(ToiletItem("주소", mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg"), "이름"))
        items.add(ToiletItem("주소1", mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg"), "ㅋㅋ"))
        items.add(ToiletItem("주소2", mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg"), "ㅇㅇ"))
        items.add(ToiletItem("주소3", mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg"), "ㄴㄴ"))
        items.add(ToiletItem("주소4", mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg"), "ㄹㄹ"))
        items.add(ToiletItem("주소5", mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg"), "ㅎㅎ"))
        items.add(ToiletItem("주소6", mutableListOf("https://cdn.pixabay.com/photo/2023/03/25/16/02/hummingbird-7876355__340.jpg"), "ㄷㄷ"))


        binding.shortRecycler.adapter = ToiletRecyclerAdapter(requireContext(), items)


        return binding.root
    }
}