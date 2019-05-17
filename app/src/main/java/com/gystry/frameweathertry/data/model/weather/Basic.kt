package com.gystry.frameweathertry.data.model.weather

import com.google.gson.annotations.SerializedName

class Basic {

    /**
     * Gson提供的 @SerializedName("city")注解，将原json中属性名为city的数据解析并赋值到cityName属性上
     */
    @SerializedName("city")
    var cityName = ""
    @SerializedName("id")
    var weatherId = ""

    lateinit var update: Update

    inner class Update {
        @SerializedName("loc")
        var updateTime = ""

        fun time() = updateTime.split(" ")[1];
    }
}