package com.jake5113.malddongkt.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.jake5113.malddongkt.R
import com.jake5113.malddongkt.databinding.ActivityMainBinding
import com.jake5113.malddongkt.main.list.ListContainerFragment
import com.jake5113.malddongkt.main.mypage.MypageFragment
import com.jake5113.malddongkt.main.map.NaverMapFragment
import com.jake5113.malddongkt.main.list.toilet.ToiletListFragment
import java.lang.System.currentTimeMillis

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val listContainerFragment = ListContainerFragment()
        val naverMapFragment = NaverMapFragment()
        val mypageFragment = MypageFragment()

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view, listContainerFragment).commit()

        // 리스트 계속 추가되는거 수정하기
        binding.bottomNavigation.setOnItemSelectedListener {
            val transaction = supportFragmentManager.beginTransaction()
            when (it.itemId) {
                R.id.item_list -> transaction.replace(R.id.fragment_container_view, ListContainerFragment())
                R.id.item_map -> transaction.replace(R.id.fragment_container_view, naverMapFragment)
                R.id.item_mypage -> transaction.replace(R.id.fragment_container_view, mypageFragment)
            }
            transaction.commit()
            true
        }
    }

    // 뒤로가기 버튼 두번 클릭시 앱 종료하기
    private var backKeyPressedTime = 0L

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (currentTimeMillis() - backKeyPressedTime > 2000) {
            backKeyPressedTime = currentTimeMillis()
            Toast.makeText(this, "종료하려면 뒤로가기 버튼을 한 번 더 누르세요", Toast.LENGTH_SHORT).show()
        } else {
            finish()
        }
    }
}
