package com.example.teste_telas_ia

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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

// TELA PARA SELEÇÃO DO NÍVEL DO IDIOMA

@Composable
fun LanguageLevelScreen(onBack: () -> Unit, onNext: () -> Unit) {
    var selectedLevel by remember { mutableStateOf("") }

    val levels = listOf(
        "Iniciante", "Básico", "Intermediário",
        "Intermediário Avançado", "Avançado", "Fluente"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                },
                title = {
                    Text(
                        text = "Comece seu\nPlano de Estudo",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        },
        bottomBar = {
            Button(
                onClick = onNext,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF6C63FF),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Avançar", fontWeight = FontWeight.Bold)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Qual seu nível no idioma ?",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(24.dp))

            levels.forEach { level ->
                OutlinedButton(
                    onClick = { selectedLevel = level },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .height(48.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = if (selectedLevel == level) Color(0xFFE0E0E0) else Color.Transparent
                    ),
                    border = BorderStroke(1.dp, Color.Black)
                ) {
                    Text(text = level, fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LanguageLevelScreenPreview() {
    LanguageLevelScreen(
        onBack = {},
        onNext = {}
    )
}
