package com.mohan.prepare.injections

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.content.SharedPreferences
import com.mohan.prepare.App
import com.mohan.prepare.Delegates.DataManagerDelegate
import com.mohan.prepare.viewmodel.NewsViewModel

class ViewModelProviderFactory() : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            val sp=App.context.getSharedPreferences(Injections.NEWS_PREF,Context.MODE_PRIVATE);
            return NewsViewModel(App.context, Injections.provideDatamanager(sp)) as T
        }
        throw IllegalStateException("unsupported viewmodel found")
    }

    companion object {
        private var INSTANCE: ViewModelProviderFactory? = null

        fun getInstance(): ViewModelProviderFactory {
            return INSTANCE ?: synchronized(ViewModelProviderFactory::class.java) {
                INSTANCE ?: ViewModelProviderFactory()
            }.also {
                INSTANCE = it
            }
        }


    }

}