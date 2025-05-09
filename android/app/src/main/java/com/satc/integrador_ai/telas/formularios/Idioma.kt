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
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
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

// TELA PARA SELEÇÃO DA LINGUAGEM

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LanguageSelectionScreen() {
    val navController = rememberNavController()
    LanguageSelectionScreen(navController = navController)
}

@Composable
fun LanguageSelectionScreen(navController: NavHostController) {
    val idiomas = listOf(
        Pair("Inglês", R.drawable.flag_usa),
        Pair("Espanhol", R.drawable.flag_spain),
        Pair("Italiano", R.drawable.flag_italy),
        Pair("Francês", R.drawable.flag_france),
        Pair("Russo", R.drawable.flag_russia),
        Pair("Chinês", R.drawable.flag_china)
    )

    var selectedLanguage by remember { mutableStateOf<String?>(null) }



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
            text = "Qual idioma deseja\nestudar ?",
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(50.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(idiomas) { (nome, bandeira) ->
                LanguageOption(
                    name = nome,
                    flagRes = bandeira,
                    isSelected = selectedLanguage == nome,
                    onClick = { selectedLanguage = nome }
                )
            }
        }

        Button(
            onClick = {
                selectedLanguage?.let {
                    navController.navigate("exercise")
                    println("Idioma selecionado: $it")
                }
            },
            enabled = selectedLanguage != null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedLanguage != null) Color(0xFF5F38FF) else Color.LightGray,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Avançar", fontSize = 16.sp)
        }
    }
}

@Composable
fun LanguageOption(
    name: String,
    flagRes: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        border = BorderStroke(2.dp, if (isSelected) Color(0xFF5F38FF) else Color.LightGray),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth(0.3f)
            .aspectRatio(1.2f)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = flagRes),
                contentDescription = name,
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = name, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }
    }
}