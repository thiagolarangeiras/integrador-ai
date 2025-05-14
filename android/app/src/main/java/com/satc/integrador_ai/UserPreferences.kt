package com.satc.integrador_ai

import android.content.Context
import android.content.SharedPreferences

object UserPreferences {
  private lateinit var prefs: SharedPreferences

  fun init(context: Context) {
    prefs = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
  }

  fun saveToken(token: String) {
    prefs.edit().putString("token", token).apply()
  }

  fun getToken(): String? {
    return prefs.getString("token", null);
  }

  fun clearToken() {
    prefs.edit().remove("token").apply()
  }
}