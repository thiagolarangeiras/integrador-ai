package com.satc.integrador_ai.telas

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import com.satc.integrador_ai.storage.ExercicioViewModel
import com.satc.integrador_ai.storage.HomeViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.satc.integrador_ai.NavigationTarget

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    //HomeScreen();
}

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavController,
    exercicioViewModel: ExercicioViewModel
) {
    homeViewModel.loadPlanoEstudo()
    homeViewModel.loadUserData()
    val usuario by homeViewModel.usuario.collectAsState()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(0, navController)
        },
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Olá, ${usuario.nomeCompleto}!",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))
            CardSection(
                title = "Inglês Intermediário",
                subtitle = "0% concluído",
                buttonText = "Ir para Plano de Estudo",
                navController = navController
            )

            Spacer(modifier = Modifier.height(20.dp))
            EstudoHojeCard(
                homeViewModel = homeViewModel,
                navController = navController,
                exercicioViewModel = exercicioViewModel
            )

            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                    .padding(16.dp)
            ) {
                Button(
                    onClick = { navController.navigate(NavigationTarget.PlanoDeEstudo.route) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7061FD)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Gerar Novos Exercícios", color = Color.White)
                }
            }
            //ProgressoCard()
        }
    }
}

@Composable
fun CardSection(title: String, subtitle: String, buttonText: String, navController: NavController ) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .border(1.dp, Color.Black, shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Text(text = title, fontWeight = FontWeight.Bold, fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 4.dp))
        Text(text = subtitle, fontSize = 14.sp, color = Color.DarkGray, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate(NavigationTarget.PlanoDeEstudo.route) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7061FD)),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = buttonText, color = Color.White)
        }
    }
}

@Composable
fun EstudoHojeCard(
    exercicioViewModel: ExercicioViewModel,
    homeViewModel: HomeViewModel,
    navController: NavController
) {
    exercicioViewModel.setExercicios(homeViewModel.getExercicios())
    val nomeEstudoHoje by homeViewModel.exercicios.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current

    // One-time navigation listener
    LaunchedEffect(Unit) {
        exercicioViewModel.navigationEvent.collect { state ->
            navController.navigate(state)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .border(1.dp, Color.Black, shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Text(text = "Estudo de Hoje", fontWeight = FontWeight.Bold, fontSize = 18.sp,
            modifier = Modifier
                .padding(bottom = 4.dp))
        Text(text = "Plano de Estudo: ${nomeEstudoHoje.nome}", fontSize = 14.sp, color = Color.DarkGray, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            LinearProgressIndicator(
                progress = { 0.0F },
                modifier = Modifier
                    .weight(1f)
                    .height(10.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = Color(0xFF7061FD),
                trackColor = Color(0xFFF4F3F3),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "30 min", fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
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

//@Composable
//fun ProgressoCard() {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(start = 16.dp, end = 16.dp)
//            .border(1.dp, Color.Black, shape = RoundedCornerShape(12.dp))
//            .padding(16.dp)
//    ) {
//        Text(text = "Seu Progresso", fontWeight = FontWeight.Bold, fontSize = 18.sp)
//        Spacer(modifier = Modifier.height(12.dp))
//        Row(
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            ProgressoItem(icon = Icons.Default.Translate, label = "Vocabulário", progresso = 0)
//        }
//        Spacer(modifier = Modifier.height(12.dp))
//        Row(
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            ProgressoItem(icon = Icons.Default.Headphones, label = "Audição", progresso = 0)
//        }
//        Spacer(modifier = Modifier.height(12.dp))
//        Row(
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            ProgressoItem(icon = Icons.Default.List, label = "Gramática", progresso = 0)
//        }
//        Spacer(modifier = Modifier.height(12.dp))
//        Row(
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            ProgressoItem(icon = Icons.Default.Mic, label = "Fala", progresso = 0)
//        }
//    }
//}
//
//@Composable
//fun ProgressoItem(icon: ImageVector, label: String, progresso: Int) {
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.spacedBy(8.dp),
//        modifier = Modifier.padding(8.dp)
//    ) {
//        Icon(
//            imageVector = icon,
//            contentDescription = label,
//            modifier = Modifier.size(36.dp)
//        )
//
//        Column(
//            verticalArrangement = Arrangement.spacedBy(2.dp)
//        ) {
//            Text(text = label, fontSize = 14.sp)
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.spacedBy(4.dp)
//            ) {
//                LinearProgressIndicator(
//                    progress = { progresso / 100f },
//                    modifier = Modifier
//                        .width(230.dp)
//                        .height(10.dp)
//                        .clip(RoundedCornerShape(4.dp)),
//                    color = if (progresso == 100) Color(0xFF7061FD) else Color(0xFFF4F3F3),
//                    trackColor = Color(0xFFF4F3F3),
//                )
//                Text(text = "$progresso%", fontSize = 14.sp)
//            }
//        }
//    }
//}
