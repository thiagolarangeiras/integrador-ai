package com.satc.integrador_ai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.satc.integrador_ai.telas.HomeScreenPreview
import com.satc.integrador_ai.telas.formularios.DificuldadeIdiomaScreen
import com.satc.integrador_ai.telas.formularios.ExerciseSelectionScreen
import com.satc.integrador_ai.telas.formularios.LanguageLevelScreen
import com.satc.integrador_ai.telas.formularios.LanguageSelectionScreen
import com.satc.integrador_ai.telas.formularios.StudyPlanScreen
import com.satc.integrador_ai.telas.formularios.TemaAssuntoScreen


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
    object TemaAssunto: Screen("temaassunto")
    object DificuldadeIdioma: Screen("dificuldadeidioma")
    object LanguageLevel: Screen("languagelevel")

    object StudyPlan : Screen("StudyPlanScreen/{level}") {
        fun createRoute(level: String) = "StudyPlanScreen/$level"
    }
}

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
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Welcome.route) {
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
        composable(Screen.Language.route) {
            LanguageSelectionScreen(navController)
        }
        composable(Screen.Exercise.route) {
            ExerciseSelectionScreen(navController)
        }
        composable(Screen.TemaAssunto.route) {
            TemaAssuntoScreen(navController)
        }
        composable(Screen.DificuldadeIdioma.route) {
            DificuldadeIdiomaScreen(
                onNext = {
                    navController.navigate(Screen.LanguageLevel.route)
                }
            )
        }
        composable(Screen.LanguageLevel.route) {
            LanguageLevelScreen(
                onNext = { selectedLevel ->
                    navController.navigate(Screen.StudyPlan.createRoute(selectedLevel))
                }
            )
        }
        composable(
            route = Screen.StudyPlan.route,
            arguments = listOf(navArgument("level") { type = NavType.StringType })
        ) { backStackEntry ->
            val level = backStackEntry.arguments?.getString("level") ?: ""
            StudyPlanScreen(level = level, onNext = { selectedDays, studyMinutes ->
                // Você pode tratar os dados aqui ou navegar para outra tela
                println("Plano criado: $selectedDays, $studyMinutes minutos")
                }
            )
        }
        composable(
            route = Screen.StudyPlan.route,
            arguments = listOf(navArgument("level") { type = NavType.StringType })
        ) { backStackEntry ->
            val level = backStackEntry.arguments?.getString("level") ?: ""

            StudyPlanScreen(
                level = level,
                onNext = { days, minutes ->
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.LanguageLevel.route) { inclusive = true }
                    }
                }
            )
        }
    }
}