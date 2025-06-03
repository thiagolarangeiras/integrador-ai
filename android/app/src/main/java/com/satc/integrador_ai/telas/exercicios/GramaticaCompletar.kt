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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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

    Scaffold(
        topBar = {
            AppTopBar(onExitClick = onExitClick,onBackClick = onBackClick, title = "Exercício\n5 de 8")
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.White
            ) {
                Button(
                    onClick = onNextClick,
                    colors = ButtonDefaults.buttonColors(Color(0xFF7061FD)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 48.dp, end = 48.dp, bottom = 24.dp)
                        .height(48.dp)
                        .imePadding(),
                    shape = RoundedCornerShape(12.dp)
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
                    text = "Gramática",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(80.dp))

                Text(
                    text = "He is interested ___ learning English .",
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
                    OutlinedButton (
                        onClick = { onOptionSelected(option) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        border = BorderStroke(1.dp, Color.Black),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = if (selectedOption == option) Color(0xFFEDE9FD) else Color.White,
                            contentColor = Color(0xFF7061FD)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(option, fontWeight = FontWeight.Medium, fontSize = 16.sp)
                    }
                }

                Spacer(modifier = Modifier.weight(1f))
            }
        }
    )
}