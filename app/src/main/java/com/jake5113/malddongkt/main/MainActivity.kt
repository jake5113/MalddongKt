package com.jake5113.malddongkt.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jake5113.malddongkt.R
import com.jake5113.malddongkt.databinding.ActivityMainBinding
import com.jake5113.malddongkt.main.favorite.FavoriteFragment
import com.jake5113.malddongkt.main.map.NaverMapFragment
import com.jake5113.malddongkt.main.list.toilet.ToiletListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, ToiletListFragment()).commit()

        val toiletListFragment = ToiletListFragment()
        val naverMapFragment = NaverMapFragment()
        val favoriteFragment = FavoriteFragment()

        // 리스트 계속 추가되는거 수정하기
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_list -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, toiletListFragment).commit()
                }

                R.id.item_map -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, naverMapFragment).commit()
                }

                R.id.item_favorite -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, favoriteFragment).commit()
                }
            }
            true
        }
    }
}
