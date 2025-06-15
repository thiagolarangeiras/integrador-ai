package com.satc.integrador_ai.storage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.satc.integrador_ai.NavigationTarget
import com.satc.integrador_ai.data.model.request.ExercicioGramaticaCompletarGetDto
import com.satc.integrador_ai.data.model.request.ExercicioGramaticaOrdemGetDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExercicioViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    private val _exercicios = MutableStateFlow<Exercicios?>(null)
    val exercicios: StateFlow<Exercicios?> = _exercicios

    private val _exercicioAtual = MutableStateFlow<TipoExercicio?>(null)
    val exercicioAtual: StateFlow<TipoExercicio?> = _exercicioAtual

    private val _exerciciosRespondidos: MutableStateFlow<List<TipoExercicio>> = MutableStateFlow(emptyList())
    val exerciciosRespondidos: StateFlow<List<TipoExercicio>> = _exerciciosRespondidos

    private val _navigationEvent = MutableSharedFlow<String>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    private val _respostaFeita = MutableStateFlow<String?>(null)
    val respostaFeita: StateFlow<String?> = _respostaFeita

    fun onNextScreen() {
        if (!getRespostaFeita().equals("") && getRespostaFeita() != null) {
            val exercicioGramaticaCompletar = _exercicioAtual.value as? ExercicioGramaticaCompletarGetDto
            if (getRespostaFeita().equals(exercicioGramaticaCompletar?.opcaoCorreta)) {
                viewModelScope.launch {
                    _respostaFeita.value = ""
                    _navigationEvent.emit(NavigationTarget.RespostaCorretaGramaticaCompletar.route)
                }
            } else {
                viewModelScope.launch {
                    _respostaFeita.value = ""
                    _navigationEvent.emit(NavigationTarget.RespostaIncorretaGramaticaCompletar.route)
                }
            }
        }

        val proximoExercicio: TipoExercicio ?= getProximoExercicio()
        if (proximoExercicio?.getTipoExercicio().equals("GramaticaCompletar")) {
            viewModelScope.launch {
                _navigationEvent.emit(NavigationTarget.ExercicioGramaticaCompletar.route)
            }
        } else if (proximoExercicio?.getTipoExercicio().equals("GramaticaOrdem")) {
            viewModelScope.launch {
                _navigationEvent.emit(NavigationTarget.ExercicioGramaticaOrdem.route)
            }
        } else if (proximoExercicio?.getTipoExercicio().equals("VocabularioPares")) {
            viewModelScope.launch {
                _navigationEvent.emit(NavigationTarget.ExercicioVocabularioPares.route)
            }
        }
    }

    fun setRespostaFeita(resposta: String) {
        _respostaFeita.value = resposta
    }

    fun getRespostaFeita(): String {
        return _respostaFeita.value.toString()
    }

    fun setExercicios(exercicios: Exercicios) {
        _exercicios.value = exercicios
    }

    fun getProximoExercicio() : TipoExercicio? {
        val proximoExercicioGramaticaCompletar: TipoExercicio ?= getProximoExercicioGramaticaCompletar()

        if (proximoExercicioGramaticaCompletar != null) {
            _exerciciosRespondidos.value.toMutableList().apply {
                add(proximoExercicioGramaticaCompletar)
            }
            _exercicioAtual.value = proximoExercicioGramaticaCompletar
            return proximoExercicioGramaticaCompletar
        }

        val proximoExercicioGramaticaOrdem: TipoExercicio ?= getProximoExercicioGramaticaOrdem()
        if (proximoExercicioGramaticaOrdem != null) {
            _exerciciosRespondidos.value.toMutableList().apply {
                add(proximoExercicioGramaticaOrdem)
            }
            _exercicioAtual.value = proximoExercicioGramaticaOrdem
            return proximoExercicioGramaticaOrdem
        }

        val proximoExercicioVocabularioPares: TipoExercicio ?= getProximoExercicioVocabularioPares()
        if (proximoExercicioVocabularioPares != null) {
            _exerciciosRespondidos.value.toMutableList().apply {
                add(proximoExercicioVocabularioPares)
            }
            _exercicioAtual.value = proximoExercicioVocabularioPares
            return proximoExercicioGramaticaOrdem
        }
        return null
    }

    fun getProximoExercicioGramaticaCompletar(): TipoExercicio? {
        val current = _exercicios.value
        val list = current?.exerGramaCompl
        if (list == null || list.isEmpty()) return null
        val proximo = list.first()
        list.removeAt(0)
        _exercicios.value = current
        return proximo
    }

    fun getProximoExercicioGramaticaOrdem(): TipoExercicio? {
        val current = _exercicios.value
        val list = current?.exerGramaOrdem
        if (list == null || list.isEmpty()) return null
        val proximo = list.first()
        list.removeAt(0)
        _exercicios.value = current
        return proximo
    }

    fun getProximoExercicioVocabularioPares(): TipoExercicio? {
        val current = _exercicios.value
        val list = current?.exerVocPares
        if (list == null || list.isEmpty()) return null
        val proximo = list.first()
        list.removeAt(0)
        _exercicios.value = current
        return proximo
    }

    fun getTitle(): String {
        val qtdRespondidosCorrigidos = if (getQtdExerciciosRespondidos() == 0) getQtdExerciciosRespondidos() + 1 else getQtdExerciciosRespondidos()
        return "Exercício\n${qtdRespondidosCorrigidos} de ${getQtdTotalExercicios()}"
    }

    fun getQtdTotalExercicios(): Int {
        val qtdExercicioGramaticaCompletar = _exercicios.value?.exerGramaCompl?.size ?: 0
        val qtdExercicioGramaticaOrdem = _exercicios.value?.exerGramaOrdem?.size ?: 0
        val qtdExercicioVocabularioPares = _exercicios.value?.exerVocPares?.size ?: 0
        val qtdExercicioRespondidos = _exerciciosRespondidos.value.size
        val qtdExercicioAtual = 1
        return qtdExercicioGramaticaCompletar + qtdExercicioGramaticaOrdem + qtdExercicioVocabularioPares + qtdExercicioRespondidos + qtdExercicioAtual
    }

    fun getQtdExerciciosRespondidos(): Int {
        val qtdExerciciosRespondidos = _exerciciosRespondidos.value.size
        return qtdExerciciosRespondidos
    }

    fun getLabelExercicio(): String {
        if(_exercicioAtual.value?.getTipoExercicio().equals("GramaticaCompletar") ||
            _exercicioAtual.value?.getTipoExercicio().equals("GramaticaOrdem"))
            return "Gramática"
        else if (_exercicioAtual.value?.getTipoExercicio().equals("VocabularioPares"))
            return "Vocabulário"
        return ""
    }

    fun getQuestaoExercicio(): String {
        if (_exercicioAtual.value?.getTipoExercicio().equals("GramaticaCompletar")) {
            val exercicioGramaticaCompletar = _exercicioAtual.value as? ExercicioGramaticaCompletarGetDto
            return exercicioGramaticaCompletar?.fraseIncompleta.toString()
        }

        if (_exercicioAtual.value?.getTipoExercicio().equals("GramaticaOrdem")) {
            val exercicioGramaticaOrdem = _exercicioAtual.value as? ExercicioGramaticaOrdemGetDto
//            return exercicioGramaticaOrdem.
        }

        if (_exercicioAtual.value?.getTipoExercicio().equals("VocabularioPares")) {
            val exercicioVocabularioPares = _exercicioAtual.value as? NavigationTarget.ExercicioVocabularioPares
//            return exercicioGramaticaOrdem.
        }
        return ""
    }

    fun getOpcoesGramaticaCompletar(): List<String> {
        val exercicioGramaticaCompletar = _exercicioAtual.value as? ExercicioGramaticaCompletarGetDto
        val todasOpcoes: MutableList<String>? = exercicioGramaticaCompletar?.opcaoIncorreta
        todasOpcoes?.add(exercicioGramaticaCompletar.opcaoCorreta.toString())
        return todasOpcoes ?: emptyList()
    }

    fun getRespostaCorreta(): String {
        val exercicioGramaticaCompletar = _exercicioAtual.value as? ExercicioGramaticaCompletarGetDto
        return exercicioGramaticaCompletar?.opcaoCorreta.toString()
    }

    fun getQuestaoAtualSplitted(): List<String>? {
        val exercicioGramaticaCompletar = _exercicioAtual.value as? ExercicioGramaticaCompletarGetDto
        return exercicioGramaticaCompletar?.opcaoCorreta?.split("___")
    }

}