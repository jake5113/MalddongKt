package com.jake5113.malddongkt.network

import com.jake5113.malddongkt.main.list.parking.ParkingResponse
import com.jake5113.malddongkt.main.list.toilet.ToiletResponse
import com.jake5113.malddongkt.main.list.touristspot.TouristResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApiService {
    // 화장실 정보 API
    @GET("6510000/publicToiletService/getPublicToiletInfoList?serviceKey=wj7oRO6dukW0QCaRyFLL%2FCVQB4H5WztM2mZlRr%2FAeP%2BvRUxW2nABknrxggyD7NHnLaOgARxnjnhDMYQEeCGgzA%3D%3D")
    fun getToiletItems(
        @Query("pageNo") pageNo: Int,
        @Query("numOfRows") numOfRows: Int
    ): Call<ToiletResponse>

    // 관광지 정보 API
    @GET("api/infoTourList?code=860565")
    fun getTouristItems(): Call<TouristResponse>

    // 주차장 정보 API
    @GET("15050093/v1/uddi:d19c8e21-4445-43fe-b2a6-865dff832e08?page=1&perPage=600&cond%5B%EC%A7%80%EC%97%AD%EC%BD%94%EB%93%9C%3A%3AEQ%5D=50110&serviceKey=wj7oRO6dukW0QCaRyFLL%2FCVQB4H5WztM2mZlRr%2FAeP%2BvRUxW2nABknrxggyD7NHnLaOgARxnjnhDMYQEeCGgzA%3D%3D")
    fun getParkingItems(): Call<ParkingResponse>
}