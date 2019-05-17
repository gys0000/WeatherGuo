package com.gystry.frameweathertry.data

/**
 * 回调的数据类
 */
data class Resourse<T>(val status: Int, val data: T?, val message: String?) {
    companion object {
        const val SUCCESS = 0
        const val ERROR = 1
        const val LOADING = 2

        fun <T> success(data: T?) = Resourse(SUCCESS, data, null)
        fun <T> error(msg: String, data: T?) = Resourse(ERROR, data, msg)
        fun <T> loading(data: T?) = Resourse(LOADING, data, null)
    }
}