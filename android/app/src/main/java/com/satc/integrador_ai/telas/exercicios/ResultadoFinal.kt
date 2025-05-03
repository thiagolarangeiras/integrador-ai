package com.satc.integrador_ai.telas.exercicios

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
            Button(
                onClick = onContinuarClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6C63FF),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Continuar", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Congrats !", fontSize = 56.sp, fontWeight = FontWeight.Bold, color = Color(0xFF6C63FF))

            Spacer(modifier = Modifier.height(40.dp))

            Text("Your Score:", fontSize = 20.sp)

            Text(
                "$score/$total",
                fontSize = 56.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6C63FF)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("+$pontos points", fontSize = 24.sp, color = Color(0xFF6C63FF), fontWeight = FontWeight.Medium)
        }
    }
}
