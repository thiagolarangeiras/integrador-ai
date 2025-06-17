package com.satc.integrador_ai.telas.exercicios

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import com.satc.integrador_ai.storage.ExercicioViewModel


@Preview(showBackground = true)
@Composable
fun RespostaIncorretaScreenPreview() {
//    RespostaIncorretaScreen()
}

@Composable
fun RespostaIncorretaScreen(exercicioViewModel: ExercicioViewModel, navController: NavController) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        exercicioViewModel.navigationEvent.collect { state ->
            navController.navigate(state)
        }
    }

    Scaffold(
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
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Próximo Exercício", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Resposta Incorreta",
                    tint = Color.White,
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color(0xFFFF4C5E), shape = CircleShape)
                        .padding(16.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text("Resposta Incorreta.", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(40.dp))
                Text("Resposta Correta:", fontSize = 18.sp, color = Color.Gray)

                Spacer(modifier = Modifier.height(12.dp))

                Box(
                    modifier = Modifier
                        .border(2.dp, Color.Black.copy(alpha = 0.1f), shape = RoundedCornerShape(12.dp))
                        .padding(32.dp)
                ) {
                    TextWithHighlightIncorrect(exercicioViewModel)
                }
            }
        }
    )
}

@Composable
fun TextWithHighlightIncorrect(exercicioViewModel: ExercicioViewModel) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            Text(exercicioViewModel.getTextoHighlight())
        }
    }
}