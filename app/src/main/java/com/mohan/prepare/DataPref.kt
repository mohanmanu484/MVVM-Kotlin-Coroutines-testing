package com.mohan.prepare

import android.content.Context
import android.content.SharedPreferences
import android.view.Display
import com.mohan.prepare.injections.Injections

open class DataPref(val sp:SharedPreferences):SharedPreferences.Editor,SharedPreferences{

    companion object {
         var INSTANCE:DataPref?=null

        fun getInstance(sp:SharedPreferences):DataPref{

            return INSTANCE?: synchronized(DataPref::class.java){
                INSTANCE?:DataPref(sp)
            }.also { INSTANCE=it }

        }


    }


    override fun edit(): SharedPreferences.Editor {
        return editor
    }

    override fun contains(key: String?): Boolean {
        return sp.contains(key)
    }

    override fun getBoolean(key: String, defValue: Boolean): Boolean {
        return sp.getBoolean(key,defValue)
    }

    override fun unregisterOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getInt(key: String, defValue: Int): Int {
        return sp.getInt(key,defValue)
    }

    override fun getAll(): MutableMap<String, *> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



    override fun getLong(key: String?, defValue: Long): Long {
      return  sp.getLong(key,defValue)
    }

    override fun getFloat(key: String?, defValue: Float): Float {
        return sp.getFloat(key,defValue)
    }

    override fun getStringSet(key: String?, defValues: MutableSet<String>?): MutableSet<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getString(key: String?, defValue: String?): String {
        return sp.getString(key,defValue)
    }

    val editor:SharedPreferences.Editor
    init {
        editor=sp.edit()
    }


    override fun clear(): SharedPreferences.Editor {
        return editor.clear().apply {
            apply()
        }
    }

    override fun putLong(key: String?, value: Long): SharedPreferences.Editor {
        return editor.putLong(key,value).apply {
            apply()
        }
    }

    override fun putInt(key: String?, value: Int): SharedPreferences.Editor {
        return editor.putInt(key,value).apply {
            apply()
        }
    }

    override fun remove(key: String?): SharedPreferences.Editor {
        return editor.remove(key).apply {
            apply()
        }
    }

    override fun putBoolean(key: String?, value: Boolean): SharedPreferences.Editor {
        return editor.putBoolean(key,value).apply {
            apply()
        }
    }

    override fun putStringSet(key: String?, values: MutableSet<String>?): SharedPreferences.Editor {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun commit(): Boolean {
        return editor.commit()
    }

    override fun putFloat(key: String?, value: Float): SharedPreferences.Editor {

        return editor.putFloat(key,value).apply {
            apply()
        }
    }

    override fun apply() {
        editor.apply()
         }

    override fun putString(key: String?, value: String?): SharedPreferences.Editor {
        return editor.putString(key,value).apply {
            apply()
        }
    }


}
