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
import androidx.compose.material3.BottomAppBar
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.satc.integrador_ai.storage.FormularioViewModel
import com.satc.integrador_ai.telas.exercicios.AppTopBar

@Preview(showBackground = true)
@Composable
fun StudyPlanScreenPreview() {
    //StudyPlanScreen( onBack = { }, onExit = { }, onNext = { });
//    StudyPlanScreen() { selectedDays, studyMinutes ->
//        // Apenas simulação: você pode usar Log ou print para ver os dados
//        println("Dias selecionados: $selectedDays")
//        println("Minutos por dia: $studyMinutes")
//    }
}

@Composable
fun StudyPlanScreen(
    onBack: () -> Unit,
    onNext: () -> Unit,
    onExit: () -> Unit,
    formularioViewModel: FormularioViewModel
) {
    var selectedDays by remember { mutableStateOf(listOf<Int>()) }
    var studyMinutes by remember { mutableStateOf(30) }

    // Par (letra visível, ID único)
    val weekDays = listOf(
        "D" to 6,
        "S" to 0,
        "T" to 1,
        "Q" to 2,
        "Q" to 3,
        "S" to 4,
        "S" to 5
    )

    Scaffold(
        topBar = {
            AppTopBar(
                onExitClick = onExit,
                onBackClick = onBack,
                title = "Comece Seu \n Plano de Estudo",
                showExitButton = false
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.White
            ) {
                Button(
                    onClick = {
                        formularioViewModel.setDiasSemana(selectedDays)
                        formularioViewModel.setTempoMinutos(studyMinutes)
                        formularioViewModel.createPreference()
                        onNext()
                    },
                    enabled = selectedDays.isNotEmpty(),
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
                    .padding(paddingValues)
                    .padding(24.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = "Quais dias da semana você pode estudar?",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(40.dp))

                Row(
                    modifier = Modifier
                        .border(2.dp, Color.Black, shape = RoundedCornerShape(24.dp))
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    weekDays.forEach { (label, id) ->
                        val isSelected = selectedDays.contains(id)
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 3.dp)
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(if (isSelected) Color(0xFF7061FD) else Color.LightGray)
                                .clickable {
                                    selectedDays = if (isSelected) {
                                        selectedDays - id
                                    } else {
                                        selectedDays + id
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = label,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(80.dp))

                Text(
                    text = "Quanto tempo diário tem disponível para estudo?",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(40.dp))

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
                    value = studyMinutes.toFloat(),
                    onValueChange = { studyMinutes = it.toInt() },
                    valueRange = 10f..120f,
                    steps = 11,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    colors = SliderDefaults.colors(
                        thumbColor = Color(0xFF7061FD),
                        activeTrackColor = Color(0xFF7061FD),
                        inactiveTrackColor = Color.LightGray
                    )
                )

                Spacer(modifier = Modifier.weight(1f))
            }
        }
    )
}