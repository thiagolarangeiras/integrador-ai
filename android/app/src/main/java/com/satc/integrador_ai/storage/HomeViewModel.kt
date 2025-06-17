package com.satc.integrador_ai.storage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.satc.integrador_ai.data.model.request.PlanoEstudoResponse
import com.satc.integrador_ai.data.repository.PlanoEstudoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val planoEstudoRepository: PlanoEstudoRepository,
    private val usuarioRepository: UsuarioRepository
) : AndroidViewModel(application) {

    private val _usuario = MutableStateFlow(Usuario(null, null, null, null, null))
    val usuario: StateFlow<Usuario> = _usuario

    private val _exercicios = MutableStateFlow(Exercicios(null, null, mutableListOf(), mutableListOf(), mutableListOf()))
    val exercicios: StateFlow<Exercicios> = _exercicios

    fun getQtExerciciosDia(): Int? {
        return _exercicios.value.qtExerciciosDia
    }

    fun getQtExerciciosGramaCompl(): Int {
        return _exercicios.value.exerGramaCompl?.size ?: 0
    }

    fun getQtExerciciosGramaOrdem(): Int {
        return _exercicios.value.exerGramaOrdem?.size ?: 0
    }

    fun getQtExerciciosVocPares(): Int {
        return _exercicios.value.exerVocPares?.size ?: 0
    }

    fun getExercicios(): Exercicios {
        return _exercicios.value
    }

    fun getUser(): Usuario {
        return _usuario.value
    }

    fun generateNewPlan() {
        viewModelScope.launch {
            try {
                planoEstudoRepository.generateNewPlan()
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
    }

    fun loadUserData() {
        viewModelScope.launch {
            try {
                var usuario: Usuario = usuarioRepository.get()
                _usuario.value.id = usuario.id
                _usuario.value.username = usuario.username
                _usuario.value.nomeCompleto = usuario.nomeCompleto
            } catch (e: Exception) {
                //throw RuntimeException(e)
            }
        }
    }

    fun loadPlanoEstudo() {
        viewModelScope.launch {
            try {
                var planoEstudo: PlanoEstudoResponse =  planoEstudoRepository.getToday()
                _exercicios.value.id = planoEstudo.id
                _exercicios.value.qtExerciciosDia = planoEstudo.qtExerciciosDia
                _exercicios.value.exerVocPares = planoEstudo.exerVocPares!!
                _exercicios.value.exerGramaCompl = planoEstudo.exerGramaCompl!!
                _exercicios.value.exerGramaOrdem = planoEstudo.exerGramaOrdem!!

                if (getQtExerciciosGramaCompl() == 0 && getQtExerciciosGramaOrdem() == 0 && getQtExerciciosVocPares() == 0) {
                    generateNewPlan()
                    loadPlanoEstudo()
                }
            } catch (e: Exception) {
                //throw RuntimeException(e)
            }
        }
    }
}