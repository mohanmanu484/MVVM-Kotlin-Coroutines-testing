package com.mohan.prepare.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.ObservableBoolean
import android.support.annotation.VisibleForTesting
import com.mohan.prepare.Delegates.DataManagerDelegate
import com.mohan.prepare.R
import com.mohan.prepare.exceptions.EmptyResultException
import com.mohan.prepare.exceptions.NoInternetException
import com.mohan.prepare.model.News
import com.mohan.prepare.util.Util
import kotlinx.coroutines.experimental.*
import retrofit2.HttpException
import java.io.IOException
import kotlin.properties.Delegates

open class NewsViewModel(val context:Context,dataManager: DataManagerDelegate) : ViewModel(), DataManagerDelegate by dataManager {

    val errorMessage=MutableLiveData<String>()
    var showProgressBar=ObservableBoolean(true)

    private val job= Job()
    val newsList:MutableLiveData<List<News>> = MutableLiveData<List<News>>()







    suspend open fun fetchData() {
        withContext(com.mohan.prepare.extensions.UI()+job){
            try {
                handleResponce()
            }catch (e:Throwable){
                showError(e)
            }
        }
    }

    private suspend fun handleResponce() {
        showProgressBar.set( true)
        val newsList = fetchDataFromManager()
        if (isDataEmpty(newsList)) {
            throw EmptyResultException()
        } else {
            updateList(newsList)
        }
        showProgressBar.set( false)
    }


     fun updateList(newsList: List<News>?) {
        this.newsList.postValue(newsList)
    }

    private fun showError(e: Throwable) {

        when(e){
            is NoInternetException->errorMessage.postValue(context.getString(R.string.check_internet))
            is EmptyResultException->errorMessage.postValue(context.getString(R.string.no_data_found))
          else ->Util.handleException(e)
        }
    }

    private fun isDataEmpty(data:List<News>?):Boolean{
        return data==null||data.isEmpty()
    }

    open suspend fun fetchDataFromManager():List<News>? {
        val data=fetchDataFromPref()
        if (isDataEmpty(data)) {

            try {
                return fetchDataFromNetwork().apply {
                    this?.let { saveDataToPref(this) }
                }?.newsList
            }catch (e:Exception){
                throw NoInternetException()
            }
        }
        return data
    }

    override fun onCleared() {
        if(!job.isCancelled) {
            job.cancel()
        }
        super.onCleared()

    }
}





