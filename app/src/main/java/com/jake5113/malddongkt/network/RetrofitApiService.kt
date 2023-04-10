package com.jake5113.malddongkt.network

import com.jake5113.malddongkt.main.list.toilet.ToiletResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApiService {
    @GET("6510000/publicToiletService/getPublicToiletInfoList?serviceKey=wj7oRO6dukW0QCaRyFLL%2FCVQB4H5WztM2mZlRr%2FAeP%2BvRUxW2nABknrxggyD7NHnLaOgARxnjnhDMYQEeCGgzA%3D%3D")
    fun getToiletItems(
        @Query("pageNo") pageNo: Int,
        @Query("numOfRows") numOfRows: Int
    ): Call<ToiletResponse>

    @GET("api/infoTourList?code=860565")
    fun getTouristItems(

    )

}