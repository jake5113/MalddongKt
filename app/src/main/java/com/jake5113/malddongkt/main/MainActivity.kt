package com.jake5113.malddongkt.main

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.jake5113.malddongkt.R
import com.jake5113.malddongkt.databinding.ActivityMainBinding
import com.jake5113.malddongkt.main.list.ListContainerFragment
import com.jake5113.malddongkt.database.ParkingResponse
import com.jake5113.malddongkt.database.ToiletResponse
import com.jake5113.malddongkt.database.TouristResponse
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

    // Fragment 생성
    val listContainerFragment = ListContainerFragment()
    val naverMapFragment = NaverMapFragment()
    val mypageFragment = MypageFragment()

    // 스피너 리스트
    var spinnerCategory = mutableListOf<String>()

    // 현재 내 위치
    var myLocation: Location? = null

    // Google Fused Location API
    val providerClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 내 위치 정보 제공 동적 퍼미션 요청
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            requestMyLocation()
        }

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

    private val permissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) requestMyLocation()
            else Toast.makeText(this, "위치정보 제공에 동의하지 않았습니다.", Toast.LENGTH_SHORT).show()
        }

    fun requestMyLocation() {
        val request: LocationRequest =
            LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000).build()

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        providerClient.requestLocationUpdates(request, locationCallback, Looper.getMainLooper())
    }

    // 위치검색 결과 콜백객체
    private val locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)

            myLocation = locationResult.lastLocation

            providerClient.removeLocationUpdates(this)

            naverMapFragment.getMyLocation(myLocation!!)
        }
    }

    fun getToiletItems() {
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
                    if (listContainerFragment.totalItemsToilet.isEmpty())
                        listContainerFragment.totalItemsToilet.addAll(toiletItemList)

                    if(naverMapFragment.totalItemsToilet.isEmpty())
                        naverMapFragment.totalItemsToilet.addAll(toiletItemList)

                    if (spinnerCategory.isEmpty()) {
                        val hashSet = hashSetOf<String>()
                        for (i in 0 until toiletItemList.size) {
                            hashSet.add(toiletItemList[i].emdNm)
                        }

                        spinnerCategory.addAll(hashSet)
                        spinnerCategory.sort()
                        spinnerCategory.add(0, "전체")
                        listContainerFragment.spinnerItemsCategory.addAll(spinnerCategory)
                        listContainerFragment.spinnerAdapter.notifyDataSetChanged()
                    }
                    listContainerFragment.binding.recycler.adapter?.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<ToiletResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "인터넷 연결을 확인해주세요", Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun getTouristSpotItems() {
        val retrofit = RetrofitHelper.getRetrofitInstance("http://api.jejuits.go.kr/")
        val retrofitApiService = retrofit.create(RetrofitApiService::class.java)
        retrofitApiService.getTouristItems().enqueue(object : Callback<TouristResponse> {
            override fun onResponse(
                call: Call<TouristResponse>,
                response: Response<TouristResponse>
            ) {
                touristResponse = response.body()
                if (listContainerFragment.totalItemsTourist.isEmpty())
                    listContainerFragment.totalItemsTourist.addAll(touristResponse?.info!!)

                if(naverMapFragment.totalItemsTourist.isEmpty())
                    naverMapFragment.totalItemsTourist.addAll(touristResponse?.info!!)

                listContainerFragment.binding.recycler.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<TouristResponse>, t: Throwable) {}
        })
    }

    fun getParkingItems() {
        val retrofit = RetrofitHelper.getRetrofitInstance("https://api.odcloud.kr/api/")
        val retrofitApiService = retrofit.create(RetrofitApiService::class.java)
        retrofitApiService.getParkingItems().enqueue(object : Callback<ParkingResponse> {
            override fun onResponse(
                call: Call<ParkingResponse>,
                response: Response<ParkingResponse>
            ) {
                parkingResponse = response.body()
                if (listContainerFragment.totalItemsParking.isEmpty())
                    listContainerFragment.totalItemsParking.addAll(parkingResponse?.data!!)

                if(naverMapFragment.totalItemsParking.isEmpty())
                    naverMapFragment.totalItemsParking.addAll(parkingResponse?.data!!)

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
