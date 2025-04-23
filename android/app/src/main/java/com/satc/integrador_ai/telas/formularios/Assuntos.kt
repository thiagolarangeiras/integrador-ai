package com.satc.integrador_ai.telas.formularios

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
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
import com.satc.integrador_ai.R

@Preview(showSystemUi = true)
@Composable
fun PreviewTemaAssuntoScreen() {
    TemaAssuntoScreen()
}


@Composable
fun TemaAssuntoScreen() {
    val opcoes = listOf(
        Triple("Viagens e Turismo", R.drawable.ic_launcher_background, false), //ic_travel
        Triple("Música e Cultura Pop", R.drawable.ic_launcher_background, false), //ic_music
        Triple("Tecnologia e Redes Sociais", R.drawable.ic_launcher_background, false), //ic_technology
        Triple("Comida e Culinária", R.drawable.ic_launcher_background, false), // ic_food
        Triple("Trabalho e Carreira", R.drawable.ic_launcher_background, false), //ic_work
        Triple("Séries, Filmes e Entretenimento", R.drawable.ic_launcher_background, false) //ic_entertainment
    )

    val selected = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Quais temas/\nassuntos deseja estudar?",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(opcoes) { (titulo, icone, _) ->
                TemaAssuntoOption(
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
            onClick = { println("Selecionados: $selected") },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(top = 24.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF5F38FF),
                contentColor = Color.White
            )
        ) {
            Text(text = "Avançar", fontSize = 18.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun TemaAssuntoOption(
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
            modifier = Modifier.padding(16.dp),
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
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        }
    }
}