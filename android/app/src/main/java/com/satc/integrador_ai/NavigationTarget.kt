package com.satc.integrador_ai

sealed class NavigationTarget(val route: String) {
    object Welcome : NavigationTarget("welcome")
    object SignUp : NavigationTarget("signup")
    object Login : NavigationTarget("login")
    object Home : NavigationTarget("home")
    object Language : NavigationTarget("language")
    object Exercise: NavigationTarget("exercise")
    object Subject: NavigationTarget("subject")
    object Difficulty: NavigationTarget("difficulty")
    object LanguageLevel: NavigationTarget("language_level")
    object StudyPlan: NavigationTarget("study_plan")
    object ExercicioGramaticaCompletar: NavigationTarget("gramatica_completar")
    object ExercicioGramaticaOrdem: NavigationTarget("gramatica_ordem")
    object ExercicioVocabularioPares: NavigationTarget("vocabulario_pares")
    object RespostaCorreta: NavigationTarget("resposta_correta")
    object RespostaIncorreta: NavigationTarget("resposta_incorreta")
}