package com.gystry.frameweathertry.data.model.place

import com.google.gson.annotations.SerializedName
import org.litepal.crud.LitePalSupport

class Province(@SerializedName("name")val provinceName:String,@SerializedName("id")val provinceCode:Int):LitePalSupport() {
    /**
     * 这个值是由litepal数据库自动赋值的，所以定义成制度类型
     */
    @Transient val id = 0
}