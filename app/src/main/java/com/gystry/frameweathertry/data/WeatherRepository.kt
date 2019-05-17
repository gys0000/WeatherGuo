package com.gystry.frameweathertry.data

import com.gystry.frameweathertry.data.db.WeatherDao
import com.gystry.frameweathertry.data.model.weather.Weather
import com.gystry.frameweathertry.data.network.CoolWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 获取天气数据的类,
 * 数据库数据和网络数据的出口
 */
class WeatherRepository private constructor(private val weatherDao: WeatherDao, private val network: CoolWeatherNetwork) {

    suspend fun getWeather(weatherId: String): Weather {
        var weather = weatherDao.getCacheWeatherInfo()
        if (weather == null) {
            weather = requestWeather(weatherId)
        }
        return weather
    }

    suspend fun refreshWeather(weatherId: String) = requestWeather(weatherId)
    suspend fun getBingPic(): String {
        var url = weatherDao.getCacheBingPic()
        if (url == null) {
            url = refreshBingPic()
        }
        return url
    }

    /**
     * 刷新
     */
    suspend fun refreshBingPic() = requestBingPic()

    /**
     * 判断天气的数据库是否为空
     */
    fun isWeatherCached() = weatherDao.getCacheWeatherInfo() != null

    /**
     * 获取数据库中的数据
     */
    fun getCachedWeather() = weatherDao.getCacheWeatherInfo()!!

    /**
     * 从网络获取数据并保存到数据库
     */
    private suspend fun requestWeather(weatherId: String) = withContext(Dispatchers.IO) {
        var heWeather = network.fetchWeather(weatherId)
        var weather = heWeather.weather!![0]
        weatherDao.cacheWeatherInfo(weather)
        weather
    }

    private suspend fun requestBingPic() = withContext(Dispatchers.IO) {
        var url = network.fetchBingPic()
        weatherDao.cacheBingPic(url)
        url
    }

    companion object {
        private lateinit var instance: WeatherRepository
        fun getInstance(weatherDao: WeatherDao, network: CoolWeatherNetwork): WeatherRepository {
            //::instance.isInitialized判断instance非空
            if (!::instance.isInitialized) {
                synchronized(WeatherRepository::class.java) {
                    if (!::instance.isInitialized) {
                        instance = WeatherRepository(weatherDao, network)
                    }
                }
            }
            return instance
        }
    }
}