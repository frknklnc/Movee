package com.example.movee.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import javax.inject.Inject

class SharedPrefHelper @Inject constructor(
    private val context: Context
) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(prefName,MODE_PRIVATE)

    fun saveSessionId(value:String):Boolean{
        val edit = sharedPreferences.edit()
        edit.putString(prefSession,value)
        return edit.commit()

    }

    fun getSessionId():String?{
        return sharedPreferences.getString(prefSession,null)
    }

    companion object{
        private const val prefName = "MOVEE_PREFERENCES"
        private const val prefSession = "LOGIN_SESSION_TOKEN"
    }

}