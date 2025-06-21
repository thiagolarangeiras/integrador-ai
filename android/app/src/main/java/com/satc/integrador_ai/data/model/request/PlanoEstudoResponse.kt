package com.satc.integrador_ai.data.model.request

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type
import java.time.LocalDate

class LocalDateAdapter : JsonDeserializer<LocalDate>, JsonSerializer<LocalDate> {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): LocalDate {
        return LocalDate.parse(json.asString)
    }

    override fun serialize(
        src: LocalDate,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement {
        return JsonPrimitive(src.toString()) // ISO-8601: yyyy-MM-dd
    }
}

class PlanoEstudoResponse (
    val id: Int? = null,
    val idUsuario: Int? = null,
    val idPreferencia: Int? = null,
    var nome: String? = null,
    var data: LocalDate? = null,
    val qtExerciciosDia: Int? = null,
    val tiposExerciciosContidos: MutableList<String>? = null,
    val exerGramaCompl: MutableList<ExercicioGramaticaCompletarGetDto>? = null,
    val exerGramaOrdem: MutableList<ExercicioGramaticaOrdemGetDto>? = null,
    val exerVocPares: MutableList<ExercicioVocabualrioParesGetDto>? = null
)