package com.gystry.frameweathertry.ui.area

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gystry.frameweathertry.data.PlaceRepository

class ChooseAreaModelFactory (private val repository: PlaceRepository):ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChooseAreaViewModel(repository) as T
    }
}