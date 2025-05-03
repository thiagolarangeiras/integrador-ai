package com.satc.integrador_ai.telas.exercicios

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun MatchPairsExerciseScreenPreview(){
    MatchPairsExerciseScreen(
        onNextClick = {},
        onBackClick = {},
        onExitClick = {}
    );
}

@Composable
fun MatchPairsExerciseScreen(
    onBackClick: () -> Unit,
    onExitClick: () -> Unit,
    onNextClick: () -> Unit
) {
    val leftWords = listOf("adeus", "oi", "Brasil", "sim", "chá")
    val rightWords = listOf("Brazil", "tea", "goodbye", "yes", "hi")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F8FB))
            .padding(16.dp)
    ) {
        // Top bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Voltar",
                    tint = Color.White,
                    modifier = Modifier
                        .background(Color(0xFF7F5AF0), CircleShape)
                        .padding(6.dp)
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Exercício", fontWeight = FontWeight.Bold)
                Text("4 de 8", color = Color.Gray, fontSize = 12.sp)
            }

            Text(
                text = "Sair",
                color = Color(0xFFB399FF),
                modifier = Modifier.clickable { onExitClick() }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Título
        Text(
            text = "Vocabulário",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Combine os pares:")

        Spacer(modifier = Modifier.height(16.dp))

        // Duas colunas lado a lado com botões
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                leftWords.forEach { word ->
                    WordBox(text = word)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            Column {
                rightWords.forEach { word ->
                    WordBox(text = word)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Botão Avançar
        Button(
            onClick = onNextClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7F5AF0))
        ) {
            Text("Avançar", color = Color.White)
        }
    }
}

@Composable
fun WordBox(text: String) {
    Box(
        modifier = Modifier
            .width(140.dp)
            .height(40.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
            .background(Color.White, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = Color(0xFF7F5AF0), fontWeight = FontWeight.Medium)
    }
}