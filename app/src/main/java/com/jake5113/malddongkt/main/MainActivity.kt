package com.jake5113.malddongkt.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jake5113.malddongkt.R
import com.jake5113.malddongkt.databinding.ActivityMainBinding
import com.jake5113.malddongkt.main.list.ListContainerFragment
import com.jake5113.malddongkt.main.list.toilet.ToiletItem
import com.jake5113.malddongkt.main.list.toilet.ToiletResponse
import com.jake5113.malddongkt.main.mypage.MypageFragment
import com.jake5113.malddongkt.main.map.NaverMapFragment
import com.jake5113.malddongkt.network.RetrofitApiService
import com.jake5113.malddongkt.network.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.System.currentTimeMillis

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // Toilet JSON API parsing
    var toiletResponse:ToiletResponse? = null
    var toiletItemList:MutableList<ToiletItem> = mutableListOf()

    val listContainerFragment = ListContainerFragment()
    val naverMapFragment = NaverMapFragment()
    val mypageFragment = MypageFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view, listContainerFragment).commit()
        // TODO 리스트 계속 추가되는거 확인하기
        binding.bottomNavigation.setOnItemSelectedListener {
            val transaction = supportFragmentManager.beginTransaction()
            when (it.itemId) {
                R.id.item_list -> transaction.replace(R.id.fragment_container_view, listContainerFragment)
                R.id.item_map -> transaction.replace(R.id.fragment_container_view, naverMapFragment)
                R.id.item_mypage -> transaction.replace(R.id.fragment_container_view, mypageFragment)
            }
            transaction.commit()
            true
        }
        getToiletItems()
        getTouristSpotItems()
    }

    private fun getToiletItems() {
        val retrofit = RetrofitHelper.getRetrofitInstance("https://apis.data.go.kr/")
        val retrofitApiService = retrofit.create(RetrofitApiService::class.java)
        retrofitApiService.getToiletItems(1,500).enqueue(
            object : Callback<ToiletResponse>{
            override fun onResponse(
                call: Call<ToiletResponse>,
                response: Response<ToiletResponse>
            ) {
                toiletResponse = response.body()
                listContainerFragment.itemsToilet.addAll(toiletResponse?.response?.body?.items?.item!!)
                listContainerFragment.binding.recycler.adapter!!.notifyDataSetChanged()

                // 테스트 토스트
                Toast.makeText(this@MainActivity, "${toiletResponse?.response?.body?.items?.item?.size}", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ToiletResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "네트워크 오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getTouristSpotItems() {
        val retrofit = RetrofitHelper.getRetrofitInstance("http://api.jejuits.go.kr/")
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
