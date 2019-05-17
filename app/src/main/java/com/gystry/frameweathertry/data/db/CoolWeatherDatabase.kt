package com.gystry.frameweathertry.data.db

/**
 * object 用法：
 * 1.将类的声明和定义该类的单例对象结合在一起（通过object就实现了单例模式）
 */
object CoolWeatherDatabase {
    private var placeDao: PlaceDao? = null
    private var weatherDao: WeatherDao? = null

    fun getPlaceDao(): PlaceDao {
        if (placeDao == null) {
            placeDao = PlaceDao()
        }
        return placeDao!!
    }

    fun getWeatherDao(): WeatherDao {
        if (weatherDao == null) weatherDao = WeatherDao()
        return weatherDao!!
    }
}
