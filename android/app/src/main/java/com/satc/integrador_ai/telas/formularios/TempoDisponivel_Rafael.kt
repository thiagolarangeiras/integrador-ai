package com.example.teste_telas_ia.rafael

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip

// TELA PARA SELEÇÃO DO TEMPO DISPONÍVEL

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyPlanScreen(onBack: () -> Unit, onNext: () -> Unit) {
var selectedDays by remember { mutableStateOf(setOf<String>()) }
var studyMinutes by remember { mutableStateOf(30f) }

val weekDays = listOf("D", "S", "T", "Q", "Q", "S", "S") // Dom -> Sáb

Scaffold(
        topBar = {
    CenterAlignedTopAppBar(
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
        containerColor = Color(0xFF6C63FF),
contentColor = Color.White
                )
                        ) {
Text("Avançar", fontWeight = FontWeight.Bold)
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
Spacer(modifier = Modifier.height(16.dp))

Text(
        text = "Quantos dias por semana\nvocê pode estudar ?",
        textAlign = TextAlign.Center,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold
)

Spacer(modifier = Modifier.height(16.dp))

// Seleção de dias
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
                    .padding(horizontal = 4.dp)
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

Spacer(modifier = Modifier.height(32.dp))

Text(
        text = "Quanto tempo diário tem\ndisponível para estudo ?",
        textAlign = TextAlign.Center,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold
)

Spacer(modifier = Modifier.height(24.dp))

// Texto acima do Slider
Box(
        contentAlignment = Alignment.Center
) {
    Text(
            text = "${studyMinutes.toInt()} min",
            modifier = Modifier
                    .background(Color.White)
                    .padding(4.dp)
                    .border(1.dp, Color.Black, shape = RoundedCornerShape(12.dp))
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

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun StudyPlanScreenPreview() {
    StudyPlanScreen(onBack = {}, onNext = {})
}