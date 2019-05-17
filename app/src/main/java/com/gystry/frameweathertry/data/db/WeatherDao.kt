package com.gystry.frameweathertry.data.db

import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.gystry.frameweathertry.WeatherApplication
import com.gystry.frameweathertry.data.model.weather.Weather

/**
 * 数据从sharePreferences存取
 */
class WeatherDao {
    /**
     * 往sharepreferences中存放数据
     */
    fun cacheWeatherInfo(weather: Weather?) {
        if (weather == null) return
        PreferenceManager.getDefaultSharedPreferences(WeatherApplication.context).edit {
            val weatherInfo = Gson().toJson(weather)
            putString("weather", weatherInfo)
        }
    }

    /**
     * 从sharepreferences中读取数据
     */
    fun getCacheWeatherInfo(): Weather? {
        val weatherInfo = PreferenceManager.getDefaultSharedPreferences(WeatherApplication.context).getString("weather", null)
        if (weatherInfo != null) {
            return Gson().fromJson(weatherInfo, Weather::class.java)
        }
        return null
    }

    fun cacheBingPic(bingPic: String?) {
        if (bingPic == null) return
        PreferenceManager.getDefaultSharedPreferences(WeatherApplication.context).edit {
            putString("bing_pic", bingPic)
        }
    }

    fun getCacheBingPic(): String? = PreferenceManager.getDefaultSharedPreferences(WeatherApplication.context).getString("bing_pic", null)

    /**
     * 扩展方法
     *
     * todo:action: SharedPreferences.Editor.() -> Unit  待了解
     * 这个扩展方法是将一个函数作为参数，action是定义的方法名，：后边是这个函数的参数
     * 方法1内的方法2使用方法1中的参数，成为闭包
     */
    private fun SharedPreferences.edit(action: SharedPreferences.Editor.() -> Unit) {
        val editor = edit()
        action(editor)
        editor.apply()
    }
}