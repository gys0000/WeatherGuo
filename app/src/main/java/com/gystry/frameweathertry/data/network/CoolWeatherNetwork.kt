package com.gystry.frameweathertry.data.network

import com.gystry.frameweathertry.data.network.api.PlaceService
import com.gystry.frameweathertry.data.network.api.WeatherService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * todo:suspend  suspendCoroutine  continuation
 *
 * 网络请求的类。网络请求的数据从这个类获取
 */
class CoolWeatherNetwork {

    private val placeService = ServiceCreator.create(PlaceService::class.java)

    private val weatherService = ServiceCreator.create(WeatherService::class.java)

    suspend fun fetchProvinceList() = placeService.getProvince().await()

    suspend fun fetchCountyList(provinceId: Int, cityId: Int) = placeService.getCounties(provinceId, cityId).await()

    suspend fun fetchCityList(provinceId: Int) = placeService.getCities(provinceId).await()

    suspend fun fetchWeather(weatherId: String) = weatherService.getWeather(weatherId).await()

    suspend fun fetchBingPic() = weatherService.getBingPic().await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("数据为空！！"))
                }
            })
        }
    }

    /**
     * 静态区域
     */
    companion object {
        private var network: CoolWeatherNetwork? = null;

        fun getInstance(): CoolWeatherNetwork {
            if (network == null) {
                synchronized(CoolWeatherNetwork::class.java) {
                    if (network == null) {
                        network = CoolWeatherNetwork()
                    }
                }
            }
            return network!!
        }
    }
}