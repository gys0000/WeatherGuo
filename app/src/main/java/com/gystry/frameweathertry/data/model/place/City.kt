package com.gystry.frameweathertry.data.model.place

import com.google.gson.annotations.SerializedName
import org.litepal.crud.LitePalSupport

/**
 * LitePalSupport 数据库的库
 */
class City(@SerializedName("name")val cityName:String,@SerializedName("id")val cityCode:Int):LitePalSupport() {

    /**
     *  @Transient 表示该属性并非一个到数据库表的字段的映射
     *  就是在给某个javabean上需要添加个属性，但是这个属性你又不希望给存到数据库中去，仅仅是做个临时变量，用一下。
     *  不修改已经存在数据库的数据的数据结构。那么这个注解就可以一用。
     *  1）一旦变量被transient修饰，变量将不再是对象持久化的一部分，该变量内容在序列化后无法获得访问。
     *  2）transient关键字只能修饰变量，而不能修饰方法和类。注意，本地变量是不能被transient关键字修饰的。变量如果是用户自定义类变量，则该类需要实现Serializable接口。
     *  3）被transient关键字修饰的变量不再能被序列化，一个静态变量不管是否被transient修饰，均不能被序列化。
     */
    @Transient
    val id=0

    var provinceId=0

}