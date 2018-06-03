package com.mohan.prepare.service

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiService{
    //https://raw.githubusercontent.com/mohanmanu484/CalendarAdapter/master/app/src/main/java/com/mohan/calendaradapter/json/news.json
    private val retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val service = retrofit.create<DataService>(DataService::class.java)
}