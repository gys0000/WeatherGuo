package com.gystry.frameweathertry.data.model.weather

import com.google.gson.annotations.SerializedName

class Forecast {
    var date:String=""//指定数据类型

    @SerializedName("tmp")
    lateinit var temperature: Temperature
    @SerializedName("cond")
    lateinit var more: More

    inner class Temperature {
        var max = ""
        var min = ""
    }

    inner class More {
        @SerializedName("txt_d")
        var info = ""
    }
}