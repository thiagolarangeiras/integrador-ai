package com.satc.integrador_ai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.satc.integrador_ai.storage.ExercicioViewModel
import com.satc.integrador_ai.storage.FormularioViewModel
import com.satc.integrador_ai.storage.PreferencesUserViewModel
import com.satc.integrador_ai.telas.HomeScreen
import com.satc.integrador_ai.telas.exercicios.GrammarExerciseScreen
import com.satc.integrador_ai.telas.exercicios.MatchPairsExerciseScreen
import com.satc.integrador_ai.telas.exercicios.RespostaCorretaScreen
import com.satc.integrador_ai.telas.exercicios.RespostaIncorretaScreen
import com.satc.integrador_ai.telas.exercicios.SentenceOrderingScreen
import com.satc.integrador_ai.telas.formularios.DifficultyScreen
import com.satc.integrador_ai.telas.formularios.ExerciseSelectionScreen
import com.satc.integrador_ai.telas.formularios.LanguageLevelScreen
import com.satc.integrador_ai.telas.formularios.LanguageSelectionScreen
import com.satc.integrador_ai.telas.formularios.StudyPlanScreen
import com.satc.integrador_ai.telas.formularios.SubjectScreen
import com.satc.integrador_ai.telas.login.LoginScreen
import com.satc.integrador_ai.telas.login.SignUpScreen
import com.satc.integrador_ai.telas.login.TalkAiWelcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    MaterialTheme {
        AppNavigation()
    }
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation(formularioViewModel: FormularioViewModel = hiltViewModel(),
                  preferencesUserViewModel: PreferencesUserViewModel = hiltViewModel(),
                  exercicioViewModel: ExercicioViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    var startDestination = NavigationTarget.Welcome.route

    if (preferencesUserViewModel.isLoggedIn()) {
        startDestination = NavigationTarget.Home.route
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(NavigationTarget.Welcome.route) {
            TalkAiWelcomeScreen(navController)
        }
        composable(NavigationTarget.SignUp.route) {
            SignUpScreen(navController)
        }
        composable(NavigationTarget.Login.route) {
            LoginScreen(navController)
        }
        composable(NavigationTarget.Home.route) {
            HomeScreen(navController = navController, exercicioViewModel = exercicioViewModel)
        }

        /*-------------------------------- FORMULARIOS ------------------------------------*/
        composable(NavigationTarget.Language.route) {
            LanguageSelectionScreen(
                onNext = {
                    navController.navigate(NavigationTarget.Exercise.route)
                },
                onBack = {},
                onExit = {},
                formularioViewModel = formularioViewModel
            )
        }
        composable(NavigationTarget.Exercise.route) {
            ExerciseSelectionScreen(
                onNext = {
                    navController.navigate(NavigationTarget.Subject.route)
                },
                onBack = {},
                onExit = {},
                formularioViewModel = formularioViewModel)
        }
        composable(NavigationTarget.Subject.route) {
            SubjectScreen(
                onNext = {
                    navController.navigate(NavigationTarget.Difficulty.route)
                },
                onBack = {},
                onExit = {},
                formularioViewModel = formularioViewModel
            )
        }
        composable(NavigationTarget.Difficulty.route) {
            DifficultyScreen(
                onNext = {
                    navController.navigate(NavigationTarget.LanguageLevel.route)
                },
                onBack = {},
                onExit = {},
                formularioViewModel = formularioViewModel)
        }
        composable(NavigationTarget.LanguageLevel.route) {
            LanguageLevelScreen(
                onNext = {
                    navController.navigate(NavigationTarget.StudyPlan.route)
                },
                onBack = {},
                onExit = {},
                formularioViewModel = formularioViewModel
            )
        }
        composable(NavigationTarget.StudyPlan.route) {
            StudyPlanScreen(
                onNext = {
                    navController.navigate(NavigationTarget.Home.route)
                },
                onBack = {},
                onExit = {},
                formularioViewModel = formularioViewModel
            )
        }
        /*-------------------------------- FORMULARIOS ------------------------------------*/

        /*-------------------------------- EXERCICIOS ------------------------------------*/
        composable(NavigationTarget.ExercicioGramaticaOrdem.route) {
            SentenceOrderingScreen()
        }
        composable(NavigationTarget.ExercicioGramaticaCompletar.route) {
            GrammarExerciseScreen(exercicioViewModel = exercicioViewModel, navController = navController)
        }
        composable(NavigationTarget.ExercicioVocabularioPares.route) {
            MatchPairsExerciseScreen()
        }
        composable(NavigationTarget.RespostaCorretaGramaticaCompletar.route) {
            RespostaCorretaScreen(exercicioViewModel = exercicioViewModel)
        }
        composable(NavigationTarget.RespostaIncorretaGramaticaCompletar.route) {
            RespostaIncorretaScreen(exercicioViewModel = exercicioViewModel)
        }
        /*-------------------------------- EXERCICIOS ------------------------------------*/
    }
}