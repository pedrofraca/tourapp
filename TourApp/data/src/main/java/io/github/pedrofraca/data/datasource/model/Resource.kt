package io.github.pedrofraca.data.datasource.model

class Resource<T>(val status: Status, private val mData: T) {
    var error: Throwable? = null
    fun <T> getData(): T? {
        return mData as T
    }

    enum class Status {
        ERROR, OK, CACHE
    }

}