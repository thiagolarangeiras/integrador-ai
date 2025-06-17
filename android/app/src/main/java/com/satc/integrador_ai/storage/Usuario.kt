package com.satc.integrador_ai.storage

import com.satc.integrador_ai.deprecated.Plano
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

class Usuario (
    var id: Int? = null,
    var username: String?,
    var email: String?,
    var nomeCompleto: String?,
    val plano: Plano?,
)

interface UsuarioService {
    @GET("usuario")
    suspend fun get(): Usuario;
}

@Singleton
class UsuarioRepository @Inject constructor(
    private val usuarioService: UsuarioService
){
    suspend fun get(): Usuario {
        return usuarioService.get()
    }
}