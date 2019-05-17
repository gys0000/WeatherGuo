package com.gystry.frameweathertry.utils

import com.gystry.frameweathertry.data.PlaceRepository
import com.gystry.frameweathertry.data.db.CoolWeatherDatabase
import com.gystry.frameweathertry.data.network.CoolWeatherNetwork
import com.gystry.frameweathertry.ui.area.ChooseAreaModelFactory

object InjectorUtil {
    private fun getPlaceRepository() = PlaceRepository.getInstance(CoolWeatherDatabase.getPlaceDao(), CoolWeatherNetwork.getInstance())
    fun getChooseAreaModelFactory() = ChooseAreaModelFactory(getPlaceRepository())
}