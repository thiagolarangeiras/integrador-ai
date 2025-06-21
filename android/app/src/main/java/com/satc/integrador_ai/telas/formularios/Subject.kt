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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.satc.integrador_ai.R
import com.satc.integrador_ai.storage.FormularioViewModel
import com.satc.integrador_ai.telas.exercicios.AppTopBar
import kotlin.toString

@Preview(showBackground = true)
@Composable
fun SubjectScreenPreview() {
    //SubjectScreen( onBack = { }, onExit = { }, onNext = { });
}

@Composable
fun SubjectScreen(
    onBack: () -> Unit,
    onNext: () -> Unit,
    onExit: () -> Unit,
    formularioViewModel: FormularioViewModel,
    navController: NavController
) {
    val opcoes = listOf(
        Triple("Viagens e Turismo", R.drawable.ic_travel, false),
        Triple("Música e Cultura Pop", R.drawable.ic_music, false),
        Triple("Tecnologia e Redes Sociais", R.drawable.ic_technology, false),
        Triple("Comida e Culinária", R.drawable.ic_food, false),
        Triple("Trabalho e Carreira", R.drawable.ic_work, false),
        Triple("Séries, Filmes e Entretenimento", R.drawable.ic_entertainment, false)
    )

    val selected = remember { mutableStateListOf<String>() }

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
                        formularioViewModel.setTemas(selected)
                        onNext()
                    },
                    enabled = selected.isNotEmpty(),
                    colors = ButtonDefaults.buttonColors(Color(0xFF7061FD)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 48.dp, end = 48.dp, bottom = 24.dp)
                        .height(48.dp)
                        .imePadding(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Avançar", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
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
                    text = "Quais assuntos deseja estudar?",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(40.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(opcoes) { (titulo, icone, _) ->
                        SubjectOption(
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
            }
        }
    )
}

@Composable
fun SubjectOption(
    title: String,
    iconRes: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        border = BorderStroke(2.dp, if (isSelected) Color(0xFF7061FD) else Color.LightGray),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.4f)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF4F3F3)
        )
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
                modifier = Modifier.size(40.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        }
    }
}