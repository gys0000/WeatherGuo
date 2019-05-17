package com.gystry.frameweathertry.data.network.api

import com.gystry.frameweathertry.data.model.place.City
import com.gystry.frameweathertry.data.model.place.County
import com.gystry.frameweathertry.data.model.place.Province
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PlaceService {

    /**
     * 获取省份列表
     */
    @GET("api/china")
    fun getProvince(): Call<MutableList<Province>>

    /**
     * 获取城市列表
     */
    @GET("api/china/{provinceId}")
    fun getCities(@Path("provinceId") provinceId: Int): Call<MutableList<City>>

    @GET("api/china/{provinceId}/{cityId}")
    fun getCounties(@Path("provinceId") provinceId: Int, @Path("cityId") cityId: Int): Call<MutableList<County>>
}