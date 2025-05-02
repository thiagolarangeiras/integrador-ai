package com.satc.integrador_ai.telas.exercicios

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
fun GrammarExerciseScreenPreview(){
    GrammarExerciseScreen(
        onBackClick = {},
        onExitClick = {},
        onNextClick = {},
        onOptionSelected = { },
        selectedOption = "Opção Selecionada"
    )
}

@Composable
fun GrammarExerciseScreen(
    onBackClick: () -> Unit,
    onOptionSelected: (String) -> Unit,
    selectedOption: String?,
    onNextClick: () -> Unit,
    onExitClick: () -> Unit
) {
    val options = listOf("for", "at", "on", "in")

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
                Text("2 de 8", color = Color.Gray, fontSize = 12.sp)
            }

            Text(
                text = "Sair",
                color = Color(0xFFB399FF),
                modifier = Modifier.clickable { onExitClick() }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Title
        Text(
            text = "Gramática",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "He is interested ___ learning English .",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Complete o espaço vazio:",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Options
        options.forEach { option ->
            OutlinedButton (
                onClick = { onOptionSelected(option) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                border = BorderStroke(1.dp, Color.Black),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (selectedOption == option) Color(0xFFEDE9FD) else Color.White,
                    contentColor = Color(0xFF7F5AF0)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(option, fontWeight = FontWeight.Medium)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Avançar button
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