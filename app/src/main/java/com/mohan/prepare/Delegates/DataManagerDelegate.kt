package com.mohan.prepare.Delegates

import com.mohan.prepare.model.News
import com.mohan.prepare.model.Responce

interface DataManagerDelegate {


    suspend fun fetchDataFromPref(): List<News>?
    suspend fun fetchDataFromNetwork():Responce?
    suspend fun getNewsById(id:Int):News
    suspend fun getAllNews():List<News>
    suspend fun saveDataToPref(data:Responce?)


}