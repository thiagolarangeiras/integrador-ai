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
import androidx.compose.foundation.layout.imePadding
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
import androidx.compose.material3.BottomAppBar
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
import androidx.navigation.NavController
import com.satc.integrador_ai.storage.FormularioViewModel
import com.satc.integrador_ai.telas.exercicios.AppTopBar

@Preview(showBackground = true)
@Composable
fun LanguageLevelScreenPreview() {
    //LanguageLevelScreen( onBack = { }, onExit = { }, onNext = { });
}

@Composable
fun LanguageLevelScreen(
    onBack: () -> Unit,
    onNext: () -> Unit,
    onExit: () -> Unit,
    formularioViewModel: FormularioViewModel,
    navController: NavController
) {
    var selectedLevel by remember { mutableStateOf("") }

    val levels = listOf(
        "Iniciante", "Básico", "Intermediário",
        "Intermediário Avançado", "Avançado", "Fluente"
    )

    Scaffold(
        topBar = {
            AppTopBar(onBackClick = onBack, title = "Comece Seu \n Plano de Estudo", showExitButton = false, navController = navController )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.White
            ) {
                Button(
                    onClick = {
                        if (selectedLevel.isNotEmpty()) {
                            formularioViewModel.setNivel(selectedLevel)
                            onNext()
                        }
                    },
                    enabled = selectedLevel.isNotEmpty(),
                    colors = ButtonDefaults.buttonColors(Color(0xFF7061FD)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 48.dp, end = 48.dp, bottom = 24.dp)
                        .height(48.dp)
                        .imePadding(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        "Avançar",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(paddingValues)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = "Qual seu nível de conhecimento?",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(40.dp))

                levels.forEach { level ->
                    val isSelected = selectedLevel == level

                    OutlinedButton(
                        onClick = { selectedLevel = level },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                            .height(48.dp),
                        border = BorderStroke(2.dp, if (isSelected) Color.Transparent else Color.LightGray),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            backgroundColor = if (isSelected) Color(0xFF5F38FF) else Color(0xFFF4F3F3),
                            contentColor = if (isSelected) Color.White else Color.Black
                        )
                    ) {
                        Text(text = level, fontSize = 16.sp)
                    }
                }

                Spacer(modifier = Modifier.weight(1f))
            }
        }
    )
}

