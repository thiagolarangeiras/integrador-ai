package com.satc.integrador_ai.storage

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.satc.integrador_ai.data.model.request.PreferenceRequest
import com.satc.integrador_ai.data.repository.PreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormularioViewModel @Inject constructor(
    application: Application,
    private val preferenceRepository: PreferenceRepository
) : AndroidViewModel(application) {

    private val _formulario = MutableStateFlow(Formulario())
    val formulario: StateFlow<Formulario> = _formulario

    fun setIdioma(idioma: String) {
        _formulario.value = _formulario.value.copy(idioma = idioma)
    }

    fun setTiposExercicios(tipoExercicios: List<String>) {
        val tipoSelecionados = tipoExercicios
        _formulario.value = _formulario.value.copy(tipoExercicio = tipoSelecionados)
    }

    fun setTemas(temas: List<String>) {
        val temasSelecionados = temas
        _formulario.value = _formulario.value.copy(temas = temasSelecionados)
    }

    fun setDificuldade(dificuldade: String) {
        _formulario.value = _formulario.value.copy(dificuldade = dificuldade)
    }

    fun setNivel(nivel: String) {
        _formulario.value = _formulario.value.copy(nivel = nivel)
    }

    fun getDificuldade(): String {
        Log.d("LOG IURI", "getDificuldade: ${_formulario.value.dificuldade}")
        if (_formulario.value.dificuldade != null) {
            return _formulario.value.dificuldade.toString()
        }
        return ""
    }

    fun setDiasSemana(dias: List<Int>) {
        _formulario.value = _formulario.value.copy(diaSemana = dias)
    }

    fun setTempoMinutos(tempoMinutos: Int) {
        _formulario.value = _formulario.value.copy(tempoMinutos = tempoMinutos)
    }

    fun createPreference() {
        viewModelScope.launch {
            try {
                //TODO: passei null no idUsuario abaixo, ainda não sei de onde recuperar a informação, o token jwt já está salvo no shared preferences do usuario
                var preferenceRequest =
                    PreferenceRequest(null,
                        _formulario.value.idioma,
                        _formulario.value.tipoExercicio,
                        _formulario.value.temas,
                        _formulario.value.dificuldade,
                        _formulario.value.nivel,
                        _formulario.value.diaSemana,
                        _formulario.value.tempoMinutos)
                preferenceRepository.createPreference(preferenceRequest)
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
    }



//    fun addTipoExercicio(novoTipo: String) {
//        val tiposAtuais = _formulario.value.tipoExercicio.toMutableList()
//        if (!tiposAtuais.contains(novoTipo)) {
//            tiposAtuais.add(novoTipo)
//            _formulario.value = _formulario.value.copy(tipoExercicio = tiposAtuais)
//        }
//    }
//
//    fun removeTipoExercicio(tipo: String) {
//        val tiposAtuais = _formulario.value.tipoExercicio.toMutableList()
//        tiposAtuais.remove(tipo)
//        _formulario.value = _formulario.value.copy(tipoExercicio = tiposAtuais)
//    }
}