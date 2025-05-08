package com.satc.integrador_ai.telas.formularios

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.satc.integrador_ai.R

//TELA PARA SELEÇÃO DE EXERCÍCIOS

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ExerciseSelectionScreen() {
    val navController = rememberNavController()
    ExerciseSelectionScreen(navController = navController)
}

//@Composable
//fun ExerciseSelectionScreen(navController: NavHostController) {
//    val exercicios = listOf(
//        Triple("Leitura", R.drawable.ic_reading, false),
//        Triple("Escrita", R.drawable.ic_writing, false),
//        Triple("Vocabulário", R.drawable.ic_vocabulary, false),
//        Triple("Gramática", R.drawable.ic_grammar, false),
//        Triple("Conversação", R.drawable.ic_conversation, false),
//        Triple("Quizzes", R.drawable.ic_quiz, false)
//    )
//
//    val selected = remember { mutableStateListOf<String>() }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White)
//            .padding(24.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Spacer(modifier = Modifier.height(12.dp))
//
//        Text(
//            text = "Comece seu\nPlano de Estudo",
//            fontSize = 20.sp,
//            fontWeight = FontWeight.Bold,
//            textAlign = TextAlign.Center
//        )
//
//        Spacer(modifier = Modifier.height(45.dp))
//
//        Text(
//            text = "Quais tipos de exercícios deseja praticar?",
//            fontSize = 28.sp,
//            fontWeight = FontWeight.Bold,
//            textAlign = TextAlign.Center,
//            modifier = Modifier.padding(bottom = 24.dp)
//        )
//
//        Spacer(modifier = Modifier.height(30.dp))
//
//        LazyVerticalGrid(
//            columns = GridCells.Fixed(2),
//            verticalArrangement = Arrangement.spacedBy(20.dp),
//            horizontalArrangement = Arrangement.spacedBy(20.dp),
//            modifier = Modifier.weight(1f)
//        ) {
//            items(exercicios) { (titulo, icone, _) ->
//                ExerciseOption(
//                    title = titulo,
//                    iconRes = icone,
//                    isSelected = selected.contains(titulo),
//                    onClick = {
//                        if (selected.contains(titulo)) {
//                            selected.remove(titulo)
//                        } else {
//                            selected.add(titulo)
//                        }
//                    }
//                )
//            }
//        }
//
//        Button(
//            onClick = {navController.navigate("temaassunto")
//                println("Exercícios selecionados: $selected")
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(60.dp)
//                .padding(top = 24.dp),
//            shape = RoundedCornerShape(16.dp),
//            colors = ButtonDefaults.buttonColors(
//                containerColor = Color(0xFF5F38FF),
//                contentColor = Color.White
//            )
//        ) {
//            Text(text = "Avançar")
//        }
//    }
//}
//
//@Composable
//fun ExerciseOption(
//    title: String,
//    iconRes: Int,
//    isSelected: Boolean,
//    onClick: () -> Unit
//) {
//    Card(
//        border = BorderStroke(2.dp, if (isSelected) Color(0xFF5F38FF) else Color.LightGray),
//        shape = RoundedCornerShape(16.dp),
//        modifier = Modifier
//            .fillMaxWidth()
//            .aspectRatio(1.2f)
//            .clickable { onClick() },
//        elevation = CardDefaults.cardElevation(4.dp)
//    ) {
//        Column(
//            modifier = Modifier.padding(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Image(
//                painter = painterResource(id = iconRes),
//                contentDescription = title,
//                modifier = Modifier.size(48.dp),
//                contentScale = ContentScale.Fit
//            )
//            Spacer(modifier = Modifier.height(12.dp))
//            Text(
//                text = title,
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Medium,
//                textAlign = TextAlign.Center
//            )
//        }
//    }
//}

@Composable
fun ExerciseSelectionScreen(navController: NavHostController) {
    val exercicios = listOf(
        Triple("Leitura", R.drawable.ic_reading, false),
        Triple("Escrita", R.drawable.ic_writing, false),
        Triple("Vocabulário", R.drawable.ic_vocabulary, false),
        Triple("Gramática", R.drawable.ic_grammar, false),
        Triple("Conversação", R.drawable.ic_conversation, false),
        Triple("Quizzes", R.drawable.ic_quiz, false)
    )

    val selected = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Comece seu\nPlano de Estudo",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(45.dp))

        Text(
            text = "Quais tipos de exercícios\ndeseja praticar?",
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(50.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(exercicios) { (titulo, icone, _) ->
                ExerciseOption(
                    title = titulo,
                    iconRes = icone,
                    isSelected = selected.contains(titulo),
                    onClick = {
                        if (selected.contains(titulo)) {
                            selected.remove(titulo)
                        } else {
                            selected.add(titulo)
                        }
                    }
                )
            }
        }

        Button(
            onClick = {
                navController.navigate("temaassunto")
                println("Exercícios selecionados: $selected")
            },
            enabled = selected.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(top = 24.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selected.isNotEmpty()) Color(0xFF5F38FF) else Color.LightGray,
                contentColor = Color.White
            )
        ) {
            Text(text = "Avançar", fontSize = 16.sp)
        }
    }
}

@Composable
fun ExerciseOption(
    title: String,
    iconRes: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        border = BorderStroke(2.dp, if (isSelected) Color(0xFF5F38FF) else Color.LightGray),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.2f)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                modifier = Modifier.size(48.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        }
    }
}
