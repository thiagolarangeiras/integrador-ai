package com.satc.integrador_ai.storage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.satc.integrador_ai.data.Exercicios
import com.satc.integrador_ai.data.PlanoEstudoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListaExerciciosViewModel @Inject constructor(
    application: Application,
    private val planoEstudoRepository: PlanoEstudoRepository,
) : AndroidViewModel(application) {
    private val _state = MutableStateFlow<Boolean>(true)
    val state: StateFlow<Boolean> = _state.asStateFlow()

    private val _exercicios = MutableStateFlow(mutableListOf<Exercicios>())
    val exercicios: StateFlow<List<Exercicios>> = _exercicios.asStateFlow()

    fun loadExercicios(){
        viewModelScope.launch {
            try {
                _exercicios.value = planoEstudoRepository.getAll(0, 50).planos!!
                _state.value = false
            } catch (e: Exception) {
                //throw RuntimeException(e)
                e.printStackTrace()
            }
        }
    }
}