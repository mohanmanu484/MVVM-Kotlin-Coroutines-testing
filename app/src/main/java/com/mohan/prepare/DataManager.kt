package com.mohan.prepare

import com.mohan.prepare.Delegates.DataManagerDelegate
import com.mohan.prepare.extensions.await
import com.mohan.prepare.model.News
import com.mohan.prepare.model.Responce
import com.mohan.prepare.service.ApiService
import com.mohan.prepare.service.DataService
import com.mohan.prepare.util.Constants
import com.mohan.prepare.util.Data
import com.mohan.prepare.util.Util

open class DataManager(val dataPref:DataPref,val apiService:DataService):DataManagerDelegate{


    companion object {

        var INSTANCE:DataManager?=null

        fun getInstance(dataPref: DataPref,apiService: DataService):DataManager{
            return INSTANCE?: synchronized(DataManager::class.java){
                INSTANCE?:DataManager(dataPref,apiService)
            }.also { INSTANCE=it }
        }
    }



    override suspend fun getNewsById(id: Int): News {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getAllNews(): List<News> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override suspend fun saveDataToPref(data:Responce?){
        dataPref.putString(Constants.REMOTE_DATA,Util.deserialize(data))
    }

    override suspend open fun fetchDataFromPref():List<News>?{
        val responce= Util.serialize<Responce>(dataPref.getString(Constants.REMOTE_DATA,"{}"))
        return responce.newsList
    }

     override suspend fun fetchDataFromNetwork(): Responce? {
       return apiService.newsList().await()
    }




}
