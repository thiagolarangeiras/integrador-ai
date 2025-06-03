package com.satc.integrador_ai.telas.exercicios

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview(showBackground = true)
@Composable
fun ResultadoFinalPreview() {
    ResultadoFinalScreen(onContinuarClick = {})
}

@Composable
fun ResultadoFinalScreen(
    score: Int = 8,
    total: Int = 10,
    pontos: Int = 80,
    onContinuarClick: () -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFFF4F3F3),
            ) {
                Button(
                    onClick = {},
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
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF4F3F3))
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Parabéns!", fontSize = 56.sp, fontWeight = FontWeight.Bold, color = Color(0xFF7061FD))

            Spacer(modifier = Modifier.height(40.dp))

            Text("Sua Pontuação:", fontSize = 20.sp)

            Text(
                "$score/$total",
                fontSize = 56.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF7061FD)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("+$pontos pontos", fontSize = 24.sp, color = Color(0xFF7061FD), fontWeight = FontWeight.Medium)
        }
    }
}