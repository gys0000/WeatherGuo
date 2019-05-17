package com.gystry.frameweathertry.utils

import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.gystry.frameweathertry.R
import androidx.databinding.DataBindingUtil
import com.gystry.frameweathertry.databinding.ForecastItemBinding
import com.gystry.frameweathertry.data.model.weather.Weather

//扩展方法的kotlin文件

@BindingAdapter("bind:loadBingPic")
fun ImageView.loadBingPic(url: String?) {
    if (url != null) Glide.with(context).load(url).into(this)
}
@BindingAdapter("bind:colorSchemeResources")
fun SwipeRefreshLayout.colorSchemeResources(resId: Int) {
    setColorSchemeResources(resId)
}

@BindingAdapter("bind:showForecast")
fun LinearLayout.showForecast(weather: Weather?) = weather?.let {
    removeAllViews()
    for (forecast in it.forecastList) {
        var view = LayoutInflater.from(context).inflate(R.layout.forecast_item, this, false)
        DataBindingUtil.bind<ForecastItemBinding>(view)?.forecast=forecast
        addView(view)
    }
}