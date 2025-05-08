package com.satc.integrador_ai.telas.formularios

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

// TELA PARA SELEÇÃO DO TEMPO DISPONÍVEL

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StudyPlanScreenPreview() {
    StudyPlanScreen(level = "Intermediário") { selectedDays, studyMinutes ->
        // Apenas simulação: você pode usar Log ou print para ver os dados
        println("Dias selecionados: $selectedDays")
        println("Minutos por dia: $studyMinutes")
    }
}

@Composable
fun StudyPlanScreen(
    level: String,
    onNext: (Set<String>, Int) -> Unit
) {
    var selectedDays by remember { mutableStateOf(setOf<String>()) }
    var studyMinutes by remember { mutableStateOf(30f) }

    val weekDays = listOf("D", "S", "T", "Q", "Q", "S", "S") // Dom -> Sáb

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
                            .clickable { /* Ex: navController.popBackStack() */ },
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
                onClick = { onNext(selectedDays, studyMinutes.toInt()) },
                enabled = selectedDays.isNotEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (selectedDays.isNotEmpty()) Color(0xFF5F38FF) else Color.LightGray,
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
                .padding(24.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Black, fontWeight = FontWeight.Bold)) {
                        append("Nível Selecionado: ")
                    }
                    withStyle(style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)) {
                        append(level)
                    }
                },
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "Quantos dias por semana\nvocê pode estudar?",
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(36.dp))

            Row(
                modifier = Modifier
                    .border(1.dp, Color.Black, shape = RoundedCornerShape(24.dp))
                    .padding(12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                weekDays.forEach { day ->
                    val isSelected = selectedDays.contains(day)
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 3.dp)
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) Color(0xFF6C63FF) else Color.LightGray)
                            .clickable {
                                selectedDays = if (isSelected) {
                                    selectedDays - day
                                } else {
                                    selectedDays + day
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = day,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(75.dp))

            Text(
                text = "Quanto tempo diário tem\ndisponível para estudo?",
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(36.dp))

            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = "${studyMinutes.toInt()} min",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .background(Color.White)
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                        .border(1.dp, Color.White, shape = RoundedCornerShape(8.dp))
                )
            }

            Slider(
                value = studyMinutes,
                onValueChange = { studyMinutes = it },
                valueRange = 10f..120f,
                steps = 11,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                colors = SliderDefaults.colors(
                    thumbColor = Color(0xFF6C63FF),
                    activeTrackColor = Color(0xFF6C63FF),
                    inactiveTrackColor = Color.LightGray
                )
            )

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}