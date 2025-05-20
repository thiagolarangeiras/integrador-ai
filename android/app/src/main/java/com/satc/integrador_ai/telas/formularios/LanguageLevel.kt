package com.satc.integrador_ai.telas.formularios

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.satc.integrador_ai.storage.FormularioViewModel

// TELA PARA SELEÇÃO DO NÍVEL DO IDIOMA

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LanguageLevelScreenPreview() {
//    LanguageLevelScreen(onNext = {})
}

@Composable
fun LanguageLevelScreen(onNext: () -> Unit, formularioViewModel: FormularioViewModel) {
    var selectedLevel by remember { mutableStateOf("") }

    val levels = listOf(
        "Iniciante", "Básico", "Intermediário",
        "Intermediário Avançado", "Avançado", "Fluente"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.LightGray,
                elevation = 4.dp,
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Comece seu\nPlano de Estudo",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                },
                navigationIcon = {
                    Box(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .size(40.dp)
                            .border(1.dp, Color.Black, shape = RoundedCornerShape(8.dp))
                            .background(Color(0xFF5F38FF), shape = RoundedCornerShape(8.dp))
                            .clickable {
                                // Substitua com navController.popBackStack() se necessário
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Voltar",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        bottomBar = {
            Button(
                onClick = {
                    if (selectedLevel.isNotEmpty()) {
                        formularioViewModel.setNivel(selectedLevel)
                        onNext()
                    }
                },
                enabled = selectedLevel.isNotEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (selectedLevel.isNotEmpty()) Color(0xFF5F38FF) else Color.LightGray,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Avançar", fontSize = 16.sp)
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
            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "Qual seu nível\nde conhecimento ?",
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(50.dp))

            levels.forEach { level ->
                val isSelected = selectedLevel == level

                OutlinedButton(
                    onClick = { selectedLevel = level },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                        .height(48.dp),
                    border = BorderStroke(1.dp, Color.Black),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = if (isSelected) Color(0xFF5F38FF) else Color.Transparent,
                        contentColor = if (isSelected) Color.White else Color.Black
                    )
                ) {
                    Text(text = level, fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}
