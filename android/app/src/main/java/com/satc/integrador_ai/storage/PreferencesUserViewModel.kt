package com.satc.integrador_ai.storage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.satc.integrador_ai.data.local.UserSharedPreferencesHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PreferencesUserViewModel @Inject constructor(
    application: Application,
    private val userSharedPreferencesHelper: UserSharedPreferencesHelper
): AndroidViewModel(application) {

    fun setLoggedUser(jwtToken: String) {
        userSharedPreferencesHelper.saveToken(jwtToken)
    }

    fun isLoggedIn(): Boolean {
        return userSharedPreferencesHelper.getToken() != null
    }

    fun logOut() {
        userSharedPreferencesHelper.cleanToken()
    }
}