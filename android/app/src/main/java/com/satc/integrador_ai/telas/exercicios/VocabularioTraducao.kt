package com.satc.integrador_ai.telas.exercicios

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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun VocabularyExerciseScreenPreview(){
    VocabularyExerciseScreen(
        onExitClick = {},
        onNextClick = {},
        onBackClick = {}
    );
}

@Composable
fun VocabularyExerciseScreen(
    onBackClick: () -> Unit,
    onExitClick: () -> Unit,
    onNextClick: () -> Unit
) {
    var userTranslation by remember { mutableStateOf("Um café por favor.") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F8FB))
            .padding(16.dp)
    ) {
        // Top Bar
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
                Text("5 de 8", color = Color.Gray, fontSize = 12.sp)
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

        Spacer(modifier = Modifier.height(24.dp))

        // Frase para traduzir
        Text(
            text = "One coffee please.",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Traduza a frase:")

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de texto
        TextField(
            value = userTranslation,
            onValueChange = { userTranslation = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

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
