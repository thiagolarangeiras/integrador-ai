package com.satc.integrador_ai.telas.exercicios

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.satc.integrador_ai.storage.ExercicioViewModel

@Preview(showBackground = true)
@Composable
fun RespostaCorretaScreenPreview() {
//    RespostaCorretaScreen()
}

@Composable
fun RespostaCorretaScreen(exercicioViewModel: ExercicioViewModel) {
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
                    imageVector = Icons.Default.Check,
                    contentDescription = "Resposta correta",
                    tint = Color.White,
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color(0xFF2ECC9B), shape = CircleShape)
                        .padding(16.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text("Bom trabalho !", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text("Você acertou a resposta.", fontSize = 18.sp, color = Color.Gray)

                Spacer(modifier = Modifier.height(32.dp))

                Box(
                    modifier = Modifier
                        .border(2.dp, Color.Black.copy(alpha = 0.1f), shape = RoundedCornerShape(12.dp))
                        .padding(32.dp)
                ) {
                    TextWithHighlightCorrect(exercicioViewModel)
                }
            }
        }
    )
}

@Composable
fun TextWithHighlightCorrect(exercicioViewModel: ExercicioViewModel) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val questaoDividia = exercicioViewModel.getQuestaoAtualSplitted()
        Row {
            Text("${questaoDividia?.get(0).toString()} ")
            Box(
                modifier = Modifier
                    .background(Color(0xFF2ECC9B), shape = CircleShape)
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text(exercicioViewModel.getRespostaCorreta(), color = Color.White)
            }
            Text(" ${questaoDividia?.get(1).toString()}")
        }
    }
}