package com.jake5113.malddongkt.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.jake5113.malddongkt.R
import com.jake5113.malddongkt.databinding.ActivityMainBinding
import com.jake5113.malddongkt.main.map.NaverMapFragment
import com.jake5113.malddongkt.main.toilet.ToiletListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, ToiletListFragment()).commit()


        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_list -> {
                    binding.appbarMap.visibility = View.GONE
                    binding.appbarList.visibility = View.VISIBLE
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, ToiletListFragment()).commit()
                }

                R.id.item_map -> {
                    binding.appbarMap.visibility = View.VISIBLE
                    binding.appbarList.visibility = View.GONE
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, NaverMapFragment()).commit()
                }

                R.id.item_favorite -> {
                    binding.appbarMap.visibility = View.GONE
                    binding.appbarList.visibility = View.VISIBLE
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, NaverMapFragment()).commit()
                }
            }
            true
        }
    }
}
