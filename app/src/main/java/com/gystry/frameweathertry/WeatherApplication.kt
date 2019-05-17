package com.gystry.frameweathertry

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

@SuppressLint("Registered")
/**
 * 继承父类默认的构造方法是父类名+()
 */
class WeatherApplication : Application() {
    override fun onCreate() {
        super.onCreate()

    }

    /**
     *   companion object {}里边可以定义一些静态的属性，常量，或者函数；相当于Java中的静态属性或者方法
     */
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}