package com.gystry.frameweathertry.data.db

import com.gystry.frameweathertry.data.model.place.City
import com.gystry.frameweathertry.data.model.place.County
import com.gystry.frameweathertry.data.model.place.Province
import org.litepal.LitePal

/**
 * litepal 数据库的查询和保存
 */
class PlaceDao {

    /**
     *查询-----
     */
    /**
     * Province::class.java 获取类对象class<Province>
     * Cat::class.java和cat.javaClass都表示Class
     * person.javaClass.kotlin == Person::class都表示KClass
     */
    fun getProvinceList(): MutableList<Province> = LitePal.findAll(Province::class.java)

    fun getCityList(provinceId:Int):MutableList<City> =LitePal.where("provinceId=?",provinceId.toString()).find(City::class.java)

    fun getCountyList(cityId:Int):MutableList<County> = LitePal.where("cityId=?",cityId.toString()).find(County::class.java)

    /**
     * 保存----
     */
    fun saveProvinceList(provinceList:List<Province>?){
        if (provinceList!=null&&provinceList.isNotEmpty()) {
            LitePal.saveAll(provinceList)
        }
    }

    fun saveCityList(cityList: List<City>?) {
        if (cityList != null && cityList.isNotEmpty()) {
            LitePal.saveAll(cityList)
        }
    }

    fun saveCountyList(countyList: List<County>?) {
        if (countyList != null && countyList.isNotEmpty()) {
            LitePal.saveAll(countyList)
        }
    }
}