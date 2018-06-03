package com.mohan.prepare.util

import com.google.gson.Gson
typealias Data = String

object Util{

    val gson=Gson()

    inline fun <reified T>serialize(data:Data):T{
        return gson.fromJson(data,T::class.java)
    }

    inline fun <T>deserialize(type:T):String{
        return gson.toJson(type)
    }

    fun handleException(e: Throwable) {
       // send data to crashlytics
    }
}