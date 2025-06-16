package com.satc.integrador_ai.storage

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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

    var posicaoExercicioAtual: Int = 1
        private set

    private val _navigationEvent = MutableSharedFlow<String>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    var respostaFeita by mutableStateOf<String?>(null)
        private set

    fun onNextScreen() {
        if (!respostaFeita.equals("") && respostaFeita != null) {
            val exercicioGramaticaCompletar = _exercicioAtual.value as? ExercicioGramaticaCompletarGetDto
            if (respostaFeita.equals(exercicioGramaticaCompletar?.opcaoCorreta)) {
                viewModelScope.launch {
                    respostaFeita = ""
                    _navigationEvent.emit(NavigationTarget.RespostaCorreta.route)
                }
            } else {
                viewModelScope.launch {
                    respostaFeita = ""
                    _navigationEvent.emit(NavigationTarget.RespostaIncorreta.route)
                }
            }
            incrementaPosicaoExercicioAtual()
            return
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

    fun setExercicios(exercicios: Exercicios) {
        _exercicios.value = exercicios
    }

    fun atualizarRespostaFeita(resposta: String) {
        respostaFeita = resposta
    }

    fun getProximoExercicio() : TipoExercicio? {
        val proximoExercicioGramaticaCompletar: TipoExercicio ?= getProximoExercicioGramaticaCompletar()
        if (proximoExercicioGramaticaCompletar != null) {
            _exercicioAtual.value = proximoExercicioGramaticaCompletar
            return proximoExercicioGramaticaCompletar
        }

        val proximoExercicioGramaticaOrdem: TipoExercicio ?= getProximoExercicioGramaticaOrdem()
        if (proximoExercicioGramaticaOrdem != null) {
            _exercicioAtual.value = proximoExercicioGramaticaOrdem
            return proximoExercicioGramaticaOrdem
        }

        val proximoExercicioVocabularioPares: TipoExercicio ?= getProximoExercicioVocabularioPares()
        if (proximoExercicioVocabularioPares != null) {
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
        return "Exercício\n${posicaoExercicioAtual} de ${getQtdTotalExercicios()}"
    }

    fun incrementaPosicaoExercicioAtual() {
        posicaoExercicioAtual += 1
    }

    fun getQtdTotalExercicios(): Int {
        val qtdExercicioGramaticaCompletar = _exercicios.value?.exerGramaCompl?.size ?: 0
        val qtdExercicioGramaticaOrdem = _exercicios.value?.exerGramaOrdem?.size ?: 0
        val qtdExercicioVocabularioPares = _exercicios.value?.exerVocPares?.size ?: 0
        return qtdExercicioGramaticaCompletar + qtdExercicioGramaticaOrdem + qtdExercicioVocabularioPares + posicaoExercicioAtual
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
        return buildList {
            exercicioGramaticaCompletar?.opcaoIncorreta?.let { addAll(it) }
            exercicioGramaticaCompletar?.opcaoCorreta?.let { add(it) }
        }
    }

    fun getRespostaCorreta(): String {
        val exercicioGramaticaCompletar = _exercicioAtual.value as? ExercicioGramaticaCompletarGetDto
        return exercicioGramaticaCompletar?.opcaoCorreta.toString()
    }

    fun getQuestaoAtualSplitted(): List<String>? {
        val exercicioGramaticaCompletar = _exercicioAtual.value as? ExercicioGramaticaCompletarGetDto
        return exercicioGramaticaCompletar?.fraseIncompleta?.split("___")
    }

}