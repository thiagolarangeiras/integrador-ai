package com.satc.integrador_ai.telas.exercicios

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun ReadingExerciseScreenPreview(){
    ReadingExerciseScreen(
        onBackClick = {},
        onExitClick = {},
        onNextClick = {}
    );
}

@Composable
fun ReadingExerciseScreen(
    onBackClick: () -> Unit,
    onExitClick: () -> Unit,
    onNextClick: () -> Unit
) {
    val scrollState = rememberScrollState()
    val text = """
        The city, once a place filled with opportunities and life, is now nothing but a dead city. Jobs have gone to automation and the population has moved away. The only people left are those with no options. Susan didn’t know anyone in the city.”
        
        “How should I put myself out there if there is no one?” she thought. The skies started turning grey and wind was sharper. She pulled her scarf more tight and looked nervously at the time. The bus was almost always late. Which was no surprise because all the buses in the dead city were either late or never actually came. 
        
        Suddenly, a woman with children
    """.trimIndent()

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
                Text("6 de 8", color = Color.Gray, fontSize = 12.sp)
            }

            Text(
                text = "Sair",
                color = Color(0xFFB399FF),
                modifier = Modifier.clickable { onExitClick() }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Leitura",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Scrollable reading content
        Box(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState)
                .padding(end = 8.dp)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Pagination indicator
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(3) { index ->
                val isSelected = index == 1
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(if (isSelected) 10.dp else 8.dp)
                        .clip(CircleShape)
                        .background(if (isSelected) Color(0xFF7F5AF0) else Color.Gray.copy(alpha = 0.4f))
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Button
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
