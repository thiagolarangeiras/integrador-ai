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
import com.satc.integrador_ai.storage.FormularioViewModel
import com.satc.integrador_ai.storage.PreferencesUserViewModel
import com.satc.integrador_ai.telas.HomeScreenPreview
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

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object SignUp : Screen("signup")
    object Login : Screen("login")
    object Home : Screen("home")
    object Language : Screen("language")
    object Exercise: Screen("exercise")
    object Subject: Screen("subject")
    object Difficulty: Screen("difficulty")
    object LanguageLevel: Screen("language_level")
    object StudyPlan: Screen("study_plan")
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
fun AppNavigation(formularioViewModel: FormularioViewModel = hiltViewModel(), preferencesUserViewModel: PreferencesUserViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    var startDestination = Screen.Welcome.route

    if (preferencesUserViewModel.isLoggedIn()) {
        startDestination = Screen.Home.route
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.Welcome.route) {
            TalkAiWelcomeScreen(navController)
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(navController)
        }
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(Screen.Home.route) {
            HomeScreenPreview(navController)
        }

        /*-------------------------------- FORMULARIOS ------------------------------------*/
        composable(Screen.Language.route) {
            LanguageSelectionScreen(
                onNext = {
                    navController.navigate(Screen.Exercise.route)
                },
                onBack = {},
                onExit = {},
                formularioViewModel = formularioViewModel
            )
        }
        composable(Screen.Exercise.route) {
            ExerciseSelectionScreen(
                onNext = {
                    navController.navigate(Screen.Subject.route)
                },
                onBack = {},
                onExit = {},
                formularioViewModel = formularioViewModel)
        }
        composable(Screen.Subject.route) {
            SubjectScreen(
                onNext = {
                    navController.navigate(Screen.Difficulty.route)
                },
                onBack = {},
                onExit = {},
                formularioViewModel = formularioViewModel
            )
        }
        composable(Screen.Difficulty.route) {
            DifficultyScreen(
                onNext = {
                    navController.navigate(Screen.LanguageLevel.route)
                },
                onBack = {},
                onExit = {},
                formularioViewModel = formularioViewModel)
        }
        composable(Screen.LanguageLevel.route) {
            LanguageLevelScreen(
                onNext = {
                    navController.navigate(Screen.StudyPlan.route)
                },
                onBack = {},
                onExit = {},
                formularioViewModel = formularioViewModel
            )
        }
        composable(Screen.StudyPlan.route) {
            StudyPlanScreen(
                onNext = {
                    navController.navigate(Screen.Home.route)
                },
                onBack = {},
                onExit = {},
                formularioViewModel = formularioViewModel
            )
        }
        /*-------------------------------- FORMULARIOS ------------------------------------*/
    }
}