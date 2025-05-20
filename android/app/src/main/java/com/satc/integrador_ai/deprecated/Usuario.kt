package com.satc.integrador_ai.deprecated

import android.util.Log
import com.satc.integrador_ai.storage.PreferencesUserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

data class LoginDto(
    val username: String,
    val password: String,
)

data class TokenDto(
    val token: String,
)

enum class Plano {
    NORMAL, GRATIS, PAGO,
}

data class UsuarioPostDto(
    val username: String,
    val email: String,
    val password: String,
    val nomeCompleto: String,
    val plano: Plano
)

data class UsuarioGetDto(
    val id: Int,
    val username: String,
    val email: String,
    val nomeCompleto: String,
    val plano: Plano,
    val jwtToken: String
)

interface ApiService {
    @POST("auth/signin")
    fun createUser(@Body user: UsuarioPostDto): Call<UsuarioGetDto>;

    @POST("auth/login")
    fun login(@Body user: LoginDto): Call<TokenDto>;
}

class ApiRepository(private val apiService: ApiService) {
    fun createUser(user: UsuarioPostDto) = apiService.createUser(user);
    fun login(user: LoginDto) = apiService.login(user);
}

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:8080"

    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

fun createUser(user: UsuarioPostDto, changeScreen: () -> Unit, preferencesUserViewModel: PreferencesUserViewModel){
    RetrofitClient.instance.createUser(user).enqueue(object : Callback<UsuarioGetDto> {
        override fun onResponse(call: Call<UsuarioGetDto>, response: Response<UsuarioGetDto>) {
            if (response.isSuccessful) {
                preferencesUserViewModel.setLoggedUser(response.body()?.jwtToken ?: "")
                changeScreen()
            } else {
                Log.d("Erro no cadastro de usuário: ", response.errorBody()?.string().orEmpty())
            }
        }

        override fun onFailure(call: Call<UsuarioGetDto>, t: Throwable) {
            Log.e("Erro no login: ", "${t.localizedMessage ?: "Erro desconhecido"}")
        }
    })
}

fun login(user: LoginDto, changeScreen: () -> Unit, preferencesUserViewModel: PreferencesUserViewModel) {
    RetrofitClient.instance.login(user).enqueue(object : Callback<TokenDto> {
        override fun onResponse(call: Call<TokenDto>, response: Response<TokenDto>) {
            if (response.isSuccessful) {
                val tokenDto = response.body()

                if (tokenDto != null) {
                    preferencesUserViewModel.setLoggedUser(response.body()?.token ?: "")
                    Log.d("Token salvo: ", tokenDto.token)
                    changeScreen()
                }
            } else {
                Log.d("Erro no login: ", response.errorBody()?.string().orEmpty())
            }
        }

        override fun onFailure(call: Call<TokenDto>, t: Throwable) {
            Log.e("Erro no login: ", "${t.localizedMessage ?: "Erro desconhecido"}")
        }
    })
}