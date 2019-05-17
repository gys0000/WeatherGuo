package com.gystry.frameweathertry.data

import com.gystry.frameweathertry.data.db.PlaceDao
import com.gystry.frameweathertry.data.network.CoolWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

/**
 * 获取地理位置数据
 *
 * 数据库数据和网络数据的出口
 */
class PlaceRepository private constructor(private val placeDao: PlaceDao, private val network: CoolWeatherNetwork) {
    /**
     *  suspend  修饰的方法起到异步的作用
     *
     */
    suspend fun getProvinceList() = withContext(Dispatchers.IO) {
        var list = placeDao.getProvinceList()
        if (list.isEmpty()) {
            list = network.fetchProvinceList()
            placeDao.saveProvinceList(list)
        }
        //返回值
        list
    }

    suspend fun getCityList(provinceId: Int) = withContext(Dispatchers.IO) {
        var list = placeDao.getCityList(provinceId)
        if (list.isEmpty()) {
            list = network.fetchCityList(provinceId)
            list.forEach { it.provinceId = provinceId }
            placeDao.saveCityList(list)
        }
        list
    }

    suspend fun getCountyList(provinceId: Int, cityId: Int) = withContext(Dispatchers.IO) {
        var list = placeDao.getCountyList(cityId)
        if (list.isEmpty()) {
            list = network.fetchCountyList(provinceId, cityId)
            list.forEach { it.cityId = cityId }
            placeDao.saveCountyList(list)
        }
        list
    }

    companion object {
        private var instance: PlaceRepository? = null

        fun getInstance(placeDao: PlaceDao, network: CoolWeatherNetwork): PlaceRepository {
            if (instance == null) {
                synchronized(PlaceRepository::class.java) {
                    if (instance == null) {
                        instance = PlaceRepository(placeDao, network)
                    }
                }
            }
            return instance!!
        }
    }
}