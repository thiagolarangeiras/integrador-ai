package com.satc.integrador_ai.storage

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.satc.integrador_ai.NavigationTarget
import com.satc.integrador_ai.data.ExercicioGramaticaCompletarGetDto
import com.satc.integrador_ai.data.ExercicioGramaticaOrdemGetDto
import com.satc.integrador_ai.data.ExercicioVocabualrioParesGetDto
import com.satc.integrador_ai.data.Exercicios
import com.satc.integrador_ai.data.TipoExercicio
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.first
import kotlin.collections.iterator
import kotlin.text.equals
import kotlin.text.isNotEmpty
import kotlin.text.isNullOrEmpty

@HiltViewModel
class ExercicioViewModel2 @Inject constructor(
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

    var respostaFeitaGramaticaCompletar by mutableStateOf<String?>(null)
        private set

    var respostaFeitaGramaticaOrdem by mutableStateOf<List<String?>>(emptyList())
        private set

    var respostaFeitaVocabularioPares by mutableStateOf(mutableMapOf<String, String>())
        private set

    fun onNextScreen() {
        val proximaTela: NavigationTarget = recuperaProximaTela()
        respostaFeitaGramaticaCompletar = ""
        respostaFeitaGramaticaOrdem = emptyList()
        respostaFeitaVocabularioPares = mutableMapOf()
        viewModelScope.launch {
            _navigationEvent.emit(proximaTela.route)
        }
    }

    fun recuperaProximaTela(): NavigationTarget {
        if (temRespostaFeita()) {
            incrementaPosicaoExercicioAtual()
            when (ehRespostaCorreta()) {
                true -> return NavigationTarget.RespostaCorreta
                false -> return NavigationTarget.RespostaIncorreta
            }
        }

        val proximoExercicio: TipoExercicio ?= getProximoExercicio()
        when(proximoExercicio) {
            is ExercicioGramaticaCompletarGetDto -> return NavigationTarget.ExercicioGramaticaCompletar
            is ExercicioGramaticaOrdemGetDto -> return NavigationTarget.ExercicioGramaticaOrdem
            is ExercicioVocabualrioParesGetDto -> {
                defineRespostaCorreta()
                return NavigationTarget.ExercicioVocabularioPares
            }
        }
        return NavigationTarget.Home
    }

    fun hasValuesSelected(): Boolean {
        return respostaFeitaVocabularioPares.isNotEmpty()
    }

    fun defineRespostaCorreta() {
        val exercicioVocabulario = _exercicioAtual.value as? ExercicioVocabualrioParesGetDto
        val mapRespostas: HashMap<String, String> = hashMapOf()
        for ((index, value) in exercicioVocabulario?.paresEsquerda?.withIndex()!!) {
            mapRespostas[value] = exercicioVocabulario.paresDireita?.get(index).toString()
        }
        exercicioVocabulario.respostaCorreta = mapRespostas
    }

    fun temRespostaFeita(): Boolean {
        val temRespostaFeita = (!respostaFeitaGramaticaCompletar.equals("") && respostaFeitaGramaticaCompletar != null) ||
                (respostaFeitaGramaticaOrdem.isNotEmpty()) || (respostaFeitaVocabularioPares.isNotEmpty())
        return temRespostaFeita
    }

    fun ehRespostaCorreta(): Boolean {
        when (_exercicioAtual.value?.getTipoExercicio()) {
            "GramaticaCompletar" -> return verificaRespostaGramaticaCompletar()
            "GramaticaOrdem" -> return verificaRespostaGramaticaOrdem()
            "VocabularioPares" -> return verificaRespostaVocabularioPares()
        }
        throw Exception("Nenhum tipo encontrado")
    }

    fun verificaRespostaGramaticaOrdem(): Boolean {
        val exercicioGramaticaOrdem = _exercicioAtual.value as? ExercicioGramaticaOrdemGetDto
        val ehRespostaCorreta = respostaFeitaGramaticaOrdem == exercicioGramaticaOrdem?.ordemCorreta
        respostaFeitaGramaticaOrdem = emptyList()
        return ehRespostaCorreta
    }

    fun verificaRespostaGramaticaCompletar(): Boolean {
        val exercicioGramaticaCompletar = _exercicioAtual.value as? ExercicioGramaticaCompletarGetDto
        val ehRespostaCorreta = respostaFeitaGramaticaCompletar.equals(exercicioGramaticaCompletar?.opcaoCorreta)
        respostaFeitaGramaticaCompletar = ""
        return ehRespostaCorreta
    }

    fun verificaRespostaVocabularioPares(): Boolean {
        val exercicioVocabualrioPares = _exercicioAtual.value as? ExercicioVocabualrioParesGetDto
        val gabarito = exercicioVocabualrioPares?.respostaCorreta
        var ehRespostaCorreta = true
        for ((key, value) in respostaFeitaVocabularioPares) {
            if (!gabarito?.get(key).equals(value)) {
                ehRespostaCorreta = false
            }
        }
        return ehRespostaCorreta
    }

    fun setExercicios(exercicios: Exercicios) {
        _exercicios.value = exercicios
    }

    fun atualizarRespostaFeitaGramaticaCompletar(resposta: String) {
        respostaFeitaGramaticaCompletar = resposta
    }

    fun addRespostaFeitaGramaticaOrdem(resposta: String) {
        respostaFeitaGramaticaOrdem = respostaFeitaGramaticaOrdem + resposta
    }

    fun addKeyRespostaFeitaVocabularioPares(resposta: String) {
        val currentMap = respostaFeitaVocabularioPares.toMutableMap()
        if (!respostaFeitaVocabularioPares[""].isNullOrEmpty()) {
            val value = currentMap.remove("") ?: return
            currentMap[resposta] = value
        } else {
            currentMap[resposta] = ""
        }
        respostaFeitaVocabularioPares = currentMap
    }

    fun addValueRespostaFeitaVocabularioPares(resposta: String) {
        val currentMap = respostaFeitaVocabularioPares.toMutableMap()
        if (respostaFeitaVocabularioPares.containsValue("")) {
            val key = currentMap.entries.find { it.value == "" }?.key
            currentMap[key.toString()] = resposta
        } else {
            currentMap[""] = resposta
        }
        respostaFeitaVocabularioPares = currentMap
    }

    fun verifyRespostaListaEsquerda(option: String): Boolean {
        return respostaFeitaVocabularioPares.containsKey(option)
    }

    fun verifyRespostaListaDireita(option: String): Boolean {
        return respostaFeitaVocabularioPares.containsValue(option)
    }

    fun hasAnyKeySelected(): Boolean {
        return respostaFeitaVocabularioPares.any { (key, value) ->
            key.isNotEmpty() && value.isEmpty()
        }
    }

    fun hasAnyValueSelected(): Boolean {
        return respostaFeitaVocabularioPares[""]?.isNotEmpty() == true
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
            return proximoExercicioVocabularioPares
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
            return exercicioGramaticaOrdem?.fraseCompleta.toString()
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

    fun getOpcoesGramaticaOrdem(): List<String> {
        val exercicioGramaticaOrdem = _exercicioAtual.value as? ExercicioGramaticaOrdemGetDto
        return buildList {
            exercicioGramaticaOrdem?.ordemAleatoria?.let { addAll(it) }
        }
    }

    fun getTextoHighlight(): String {
        when(_exercicioAtual.value) {
            is ExercicioGramaticaCompletarGetDto -> return getTextoHighlightGramaticaCompletar()
            is ExercicioGramaticaOrdemGetDto -> return getTextoHighlightGramaticaOrdem()
            is ExercicioVocabualrioParesGetDto -> return ""
        }
        return ""
    }

    fun getTextoHighlightGramaticaCompletar(): String {
        val exercicioGramaticaCompletar = _exercicioAtual.value as? ExercicioGramaticaCompletarGetDto
        val fraseSplitted: List<String>? = exercicioGramaticaCompletar?.fraseIncompleta?.split("___")
        return fraseSplitted?.get(0) + exercicioGramaticaCompletar?.opcaoCorreta + fraseSplitted?.get(1)
    }

    fun getTextoHighlightGramaticaOrdem(): String {
        val exercicioGramaticaOrdem = _exercicioAtual.value as? ExercicioGramaticaOrdemGetDto
        return exercicioGramaticaOrdem?.ordemCorreta?.joinToString(" ").toString()
    }

    fun getListaEsquerda(): List<String> {
        val exercicioVocabularioPares = _exercicioAtual.value as? ExercicioVocabualrioParesGetDto
        return buildList {
            exercicioVocabularioPares?.paresEsquerda?.let {
                addAll(it)
            }
        }
    }

    fun getListaDireita(): List<String> {
        val exercicioVocabularioPares = _exercicioAtual.value as? ExercicioVocabualrioParesGetDto
        return buildList {
            exercicioVocabularioPares?.paresDireita?.let {
                addAll(it)
            }
        }
    }
}