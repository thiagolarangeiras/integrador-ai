package com.satc.integrador_ai.telas

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.satc.integrador_ai.data.Exercicios
import com.satc.integrador_ai.storage.ExercicioViewModel
import com.satc.integrador_ai.storage.ExercicioViewModel2
import com.satc.integrador_ai.storage.ListaExerciciosViewModel

@Preview(showBackground = true)
@Composable
fun PlanoDeEstudoScreenPreview(
    listaExerciciosViewModel: ListaExerciciosViewModel = hiltViewModel(),
) {
    val navController = rememberNavController()
    PlanoDeEstudoScreen(
        navController = navController,
        listaExerciciosViewModel = listaExerciciosViewModel
    )
}

@Composable
fun PlanoDeEstudoScreen(
    navController: NavController,
    listaExerciciosViewModel: ListaExerciciosViewModel = hiltViewModel(),
    exercicioViewModel: ExercicioViewModel2 = hiltViewModel(),
) {
    listaExerciciosViewModel.loadExercicios()
    val exercicios by listaExerciciosViewModel.exercicios.collectAsState(initial = emptyList())
    val state by listaExerciciosViewModel.state.collectAsState()

    Scaffold(
        bottomBar = { BottomNavigationBar(1, navController) },
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Plano de Estudo",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(40.dp))

            //DiasDaSemanaSelecionavel(diaSelecionado = 4)

            if (state) {
                CircularProgressIndicator()
            } else {
                exercicios.forEach { item ->
                    TarefaItem(
                        navController = navController,
                        exercicios = item,
                        exercicioViewModel = exercicioViewModel,
                        titulo = "${item.data}",
                        descricao = "${item.nome}",
                        progresso = item.qtExerciciosFinalizados!!,
                        total = item.qtExercicios!!,
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun TarefaItem(
    navController: NavController,
    exercicios: Exercicios,
    exercicioViewModel: ExercicioViewModel2,
    titulo: String,
    descricao: String,
    progresso: Int,
    total: Int
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    // One-time navigation listener
    LaunchedEffect(Unit) {
        exercicioViewModel.navigationEvent.collect { state ->
            navController.navigate(state)
        }
    }
    val progressoPercentual = if (total > 0) progresso.toFloat() / total else 0f

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
            .border(1.dp, Color.Black, shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Text(text = titulo, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = descricao, fontSize = 14.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Exercícios $progresso/$total ",
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            modifier = Modifier.align(Alignment.Start),
            color = Color.Black
        )
        LinearProgressIndicator(
            progress = { progressoPercentual.coerceIn(0f, 1f) },
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = Color(0xFF7061FD),
            trackColor = Color(0xFFBDBDBD)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                exercicioViewModel.setExercicios(exercicios)
                exercicioViewModel.onNextScreen()
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7061FD)),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Começar Agora", color = Color.White)
        }

    }
}

@Composable
fun DiasDaSemanaSelecionavel(diaSelecionado: Int) {
    val dias = listOf("DOM", "SEG", "TER", "QUA", "QUI", "SEX", "SÁB")
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        dias.forEachIndexed { index, dia ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = dia, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(
                            if (index + 1 == diaSelecionado) Color(0xFF7061FD) else Color(0xFFE0E0E0)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${index + 1}",
                        color = if (index + 1 == diaSelecionado) Color.White else Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}