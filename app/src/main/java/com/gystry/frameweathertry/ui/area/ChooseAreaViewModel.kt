package com.gystry.frameweathertry.ui.area

import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gystry.frameweathertry.WeatherApplication
import com.gystry.frameweathertry.data.PlaceRepository
import com.gystry.frameweathertry.data.model.place.City
import com.gystry.frameweathertry.data.model.place.County
import com.gystry.frameweathertry.data.model.place.Province
import com.gystry.frameweathertry.ui.area.ChooseAreaFragment.Companion.LEVEL_CITY
import com.gystry.frameweathertry.ui.area.ChooseAreaFragment.Companion.LEVEL_COUNTY
import com.gystry.frameweathertry.ui.area.ChooseAreaFragment.Companion.LEVEL_PROVINCE
import kotlinx.coroutines.launch

/**
 * ViewModel
 */
class ChooseAreaViewModel(private val reposittory: PlaceRepository) : ViewModel() {
    val currentLevel = MutableLiveData<Int>()

    var dataChanged = MutableLiveData<Int>()

    var isLoading = MutableLiveData<Boolean>()

    var areaSelected = MutableLiveData<Boolean>()

    var selectedProvince: Province? = null

    var selectedCity: City? = null

    var selectedCounty: County? = null

    lateinit var province: MutableList<Province>

    lateinit var cities: MutableList<City>

    lateinit var counties: MutableList<County>

    val dataList = ArrayList<String>()

    /**
     * 获取省名字的列表
     */
    fun getProvince() {
        currentLevel.value = LEVEL_PROVINCE
        launch {
            province = reposittory.getProvinceList()//异步获取数据，用suspend
            /**
             * 将province列表转化成string列表
             */
            dataList.addAll(province.map { it.provinceName })
        }
    }

    /**
     * let 就是将这个对象当作参数，然后用it表示
     */
    private fun getCities() = selectedProvince?.let {
        currentLevel.value = LEVEL_CITY
        launch {
            cities = reposittory.getCityList(it.provinceCode)
            dataList.addAll(cities.map { it.cityName })
        }
    }

    private fun getCounties() = selectedCity?.let {
        currentLevel.value = LEVEL_COUNTY
        launch {
            counties = reposittory.getCountyList(it.provinceId, it.cityCode)
            dataList.addAll(counties.map { it.countyName })
        }
    }

    fun onListViewItemClick(parent: AdapterView<*>, view: View, position:Int, id:Long){
        when{

        }
    }

    private fun launch(block: suspend () -> Unit) = viewModelScope.launch {
        try {
            isLoading.value = true
            dataList.clear()
            block
            dataChanged.value = dataChanged.value?.plus(1)
            isLoading.value = false
        } catch (t: Throwable) {
            t.printStackTrace()
            Toast.makeText(WeatherApplication.context, t.message, Toast.LENGTH_SHORT).show()
            dataChanged.value = dataChanged.value?.plus(1)
            isLoading.value = false
        }

    }
}