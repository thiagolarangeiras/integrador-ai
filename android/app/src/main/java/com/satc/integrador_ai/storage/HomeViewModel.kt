package com.satc.integrador_ai.storage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.satc.integrador_ai.data.Exercicios
import com.satc.integrador_ai.data.PlanoEstudoLista
import com.satc.integrador_ai.data.PlanoEstudoRepository
import com.satc.integrador_ai.data.PlanoEstudoResponse
import com.satc.integrador_ai.data.PreferenceRepository
import com.satc.integrador_ai.data.PreferenceResponse
import com.satc.integrador_ai.data.Usuario
import com.satc.integrador_ai.data.UsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val planoEstudoRepository: PlanoEstudoRepository,
    private val usuarioRepository: UsuarioRepository,
    private val preferenceRepository: PreferenceRepository
) : AndroidViewModel(application) {

    private val _usuario = MutableStateFlow(Usuario())
    val usuario: StateFlow<Usuario> = _usuario

    private val _exercicios = MutableStateFlow(Exercicios())
    val exercicios: StateFlow<Exercicios> = _exercicios

    fun getQtExerciciosDia(): Int? {
        return _exercicios.value.qtExercicios
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

    fun generateNewPlanOnDemand(func: ()-> Unit){
        viewModelScope.launch {
            try {
                planoEstudoRepository.generateNewPlan()
                var planoEstudo: PlanoEstudoResponse =  planoEstudoRepository.getToday()
                _exercicios.value = Exercicios(
                    planoEstudo.id,
                    planoEstudo.nome,
                    planoEstudo.data,
                    planoEstudo.qtExercicios,
                    planoEstudo.qtExerciciosFinalizados,
                    planoEstudo.exerGramaCompl!!,
                    planoEstudo.exerGramaOrdem!!,
                    planoEstudo.exerVocPares!!,
                )
                func()
            } catch (e: Exception) {
                throw RuntimeException(e)
                e.printStackTrace()
            }
        }
    }

    fun generateNewPlan() {
        viewModelScope.launch {
            try {
                planoEstudoRepository.generateNewPlan()
            } catch (e: Exception) {
                //throw RuntimeException(e)
                e.printStackTrace()
            }
        }
    }

    fun loadUserData() {
        viewModelScope.launch {
            try {
                val usuario = usuarioRepository.get()
                _usuario.value = usuario
            } catch (e: Exception) {
                //throw RuntimeException(e)
                e.printStackTrace()
            }
        }
    }

    private val _planoEstudo = MutableStateFlow(PlanoEstudoLista())
    val planoEstudo: StateFlow<PlanoEstudoLista> = _planoEstudo
    fun loadPlanoEstudoLista(){
        viewModelScope.launch {
            try {
                _planoEstudo.value = planoEstudoRepository.getAll(0, 50)
            } catch (e: Exception) {
                //throw RuntimeException(e)
                e.printStackTrace()
            }
        }
    }

    fun loadPlanoEstudo() {
        viewModelScope.launch {
            try {
                var planoEstudo: PlanoEstudoResponse =  planoEstudoRepository.getToday()
                _exercicios.value = Exercicios(
                    planoEstudo.id,
                    planoEstudo.nome,
                    planoEstudo.data,
                    planoEstudo.qtExercicios,
                    planoEstudo.qtExerciciosFinalizados,
                    planoEstudo.exerGramaCompl!!,
                    planoEstudo.exerGramaOrdem!!,
                    planoEstudo.exerVocPares!!,
                )
                if (getQtExerciciosGramaCompl() == 0 && getQtExerciciosGramaOrdem() == 0 && getQtExerciciosVocPares() == 0) {
                    generateNewPlan()
                    loadPlanoEstudo()
                }
            } catch (e: Exception) {
                //throw RuntimeException(e)
                e.printStackTrace()
            }
        }
    }

    private val _preference = MutableStateFlow(PreferenceResponse())
    val preference: StateFlow<PreferenceResponse> = _preference
    fun loadPreference() {
        viewModelScope.launch {
            try {
                _preference.value = preferenceRepository.getPreference()
            } catch (e: Exception) {
                //throw RuntimeException(e)
                e.printStackTrace()
            }
        }
    }
}