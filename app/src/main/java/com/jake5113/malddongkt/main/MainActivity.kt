package com.jake5113.malddongkt.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jake5113.malddongkt.R
import com.jake5113.malddongkt.databinding.ActivityMainBinding
import com.jake5113.malddongkt.main.list.ListContainerFragment
import com.jake5113.malddongkt.main.list.parking.ParkingResponse
import com.jake5113.malddongkt.main.list.toilet.ToiletResponse
import com.jake5113.malddongkt.main.list.touristspot.TouristResponse
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
    var toiletResponse: ToiletResponse? = null
    var touristResponse: TouristResponse? = null
    var parkingResponse: ParkingResponse? = null

    val listContainerFragment = ListContainerFragment()
    val naverMapFragment = NaverMapFragment()
    val mypageFragment = MypageFragment()

    var spinnerCategory = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getToiletItems()
        getTouristSpotItems()
        getParkingItems()

        // 리스트 프래그먼트 열기
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, listContainerFragment).commit()
        binding.bottomNavigation.setOnItemSelectedListener {
            val transaction = supportFragmentManager.beginTransaction()
            when (it.itemId) {
                R.id.item_list -> transaction.replace(
                    R.id.fragment_container_view,
                    listContainerFragment
                )

                R.id.item_map -> transaction.replace(R.id.fragment_container_view, naverMapFragment)
                R.id.item_mypage -> transaction.replace(
                    R.id.fragment_container_view,
                    mypageFragment
                )
            }
            transaction.commit()
            true
        }
    }

    private fun getToiletItems() {
        val retrofit = RetrofitHelper.getRetrofitInstance("https://apis.data.go.kr/")
        val retrofitApiService = retrofit.create(RetrofitApiService::class.java)
        retrofitApiService.getToiletItems(1, 500).enqueue(
            object : Callback<ToiletResponse> {
                override fun onResponse(
                    call: Call<ToiletResponse>,
                    response: Response<ToiletResponse>
                ) {
                    toiletResponse = response.body()
                    val toiletItemList = toiletResponse?.response?.body?.items?.item!!
                    listContainerFragment.totalItemsToilet.addAll(toiletItemList)

                    val hashSet = hashSetOf<String>()
                    for (i in 0 until toiletItemList.size) {
                        hashSet.add(toiletItemList[i].emdNm)
                    }

                    spinnerCategory.addAll(hashSet)
                    spinnerCategory.sort()
                    spinnerCategory.add(0, "전체")
                    listContainerFragment.spinnerItemsCategory.addAll(spinnerCategory)
                    listContainerFragment.spinnerAdapter.notifyDataSetChanged()

                    listContainerFragment.binding.recycler.adapter!!.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<ToiletResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "인터넷 연결을 확인해주세요", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun getTouristSpotItems() {
        val retrofit = RetrofitHelper.getRetrofitInstance("http://api.jejuits.go.kr/")
        val retrofitApiService = retrofit.create(RetrofitApiService::class.java)
        retrofitApiService.getTouristItems().enqueue(object : Callback<TouristResponse> {
            override fun onResponse(
                call: Call<TouristResponse>,
                response: Response<TouristResponse>
            ) {
                touristResponse = response.body()
                listContainerFragment.totalItemsTourist.addAll(touristResponse?.info!!)
                listContainerFragment.binding.recycler.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<TouristResponse>, t: Throwable) {}
        })
    }

    private fun getParkingItems() {
        val retrofit = RetrofitHelper.getRetrofitInstance("https://api.odcloud.kr/api/")
        val retrofitApiService = retrofit.create(RetrofitApiService::class.java)
        retrofitApiService.getParkingItems().enqueue(object : Callback<ParkingResponse> {
            override fun onResponse(
                call: Call<ParkingResponse>,
                response: Response<ParkingResponse>
            ) {
                parkingResponse = response.body()
                listContainerFragment.totalItemsParking.addAll(parkingResponse?.data!!)
                listContainerFragment.binding.recycler.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ParkingResponse>, t: Throwable) {}
        })
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
