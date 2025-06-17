package com.satc.integrador_ai.telas.exercicios

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import com.satc.integrador_ai.storage.ExercicioViewModel

@Preview
@Composable
fun GrammarExerciseScreenPreview(){
//    GrammarExerciseScreen()
}

@Composable
fun GrammarExerciseScreen(exercicioViewModel: ExercicioViewModel, navController: NavController) {
    val lifecycleOwner = LocalLifecycleOwner.current

    // One-time navigation listener
    LaunchedEffect(Unit) {
        exercicioViewModel.navigationEvent.collect { state ->
            navController.navigate(state)
        }
    }

    val selectedOption = exercicioViewModel.respostaFeitaGramaticaCompletar
    val options = remember {
        exercicioViewModel.getOpcoesGramaticaCompletar().shuffled()
    }

    Scaffold(
        topBar = {
            AppTopBar(onExitClick = {}, onBackClick = {}, title = exercicioViewModel.getTitle())
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.White
            ) {
                Button(
                    onClick = { exercicioViewModel.onNextScreen() },
                    colors = ButtonDefaults.buttonColors(Color(0xFF7061FD)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 48.dp, end = 48.dp, bottom = 24.dp)
                        .height(48.dp)
                        .imePadding(),
                    shape = RoundedCornerShape(12.dp),
                    enabled = selectedOption != null
                ) {
                    Text("Avançar", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(padding)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(40.dp))

                // Title
                Text(
                    text = exercicioViewModel.getLabelExercicio(),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(80.dp))

                Text(
                    text = exercicioViewModel.getQuestaoExercicio(),
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = "Complete o espaço vazio:",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Options
                options.forEach { option ->
                    SelectableButton(
                        option = option,
                        selectedOption = selectedOption,
                        onClick = { exercicioViewModel.atualizarRespostaFeitaGramaticaCompletar(option) }
                    )
                }

                Spacer(modifier = Modifier.weight(1f))
            }
        }
    )
}

@Composable
fun SelectableButton(
    option: String,
    selectedOption: String?,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        border = BorderStroke(
            1.dp,
            if (selectedOption == option) Color(0xFF7061FD) else Color.Black
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (selectedOption == option) Color(0xFFEDE9FD) else Color.White,
            contentColor = Color(0xFF7061FD)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = option,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )
    }
}