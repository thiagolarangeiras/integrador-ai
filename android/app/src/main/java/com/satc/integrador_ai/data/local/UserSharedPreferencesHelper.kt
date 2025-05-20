package com.satc.integrador_ai.data.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserSharedPreferencesHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val sharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

    fun saveToken(jwtToken: String) {
        sharedPreferences.edit().putString("jwt_token", jwtToken).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("jwt_token", null)
    }

}