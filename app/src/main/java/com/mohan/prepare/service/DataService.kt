package com.mohan.prepare.service

import com.mohan.prepare.model.Responce
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface DataService {


    @GET("mohanmanu484/CalendarAdapter/master/app/src/main/java/com/mohan/calendaradapter/json/cricnews.json")
    fun newsList(): Call<Responce>
}