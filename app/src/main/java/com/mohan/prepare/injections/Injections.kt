package com.mohan.prepare.injections

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.content.SharedPreferences
import com.mohan.prepare.App
import com.mohan.prepare.DataManager
import com.mohan.prepare.DataPref
import com.mohan.prepare.Delegates.DataManagerDelegate
import com.mohan.prepare.service.ApiService
import com.mohan.prepare.viewmodel.NewsViewModel

object Injections{
    const val NEWS_PREF="newspref"

    fun provideDatamanager(sp:SharedPreferences): DataManagerDelegate {
        val dataPref=DataPref.getInstance(sp)
       return DataManager.getInstance(dataPref,ApiService.service)
    }

    fun provideSharedPref(): SharedPreferences {
        return App.context.getSharedPreferences(NEWS_PREF,Context.MODE_PRIVATE)
    }
}