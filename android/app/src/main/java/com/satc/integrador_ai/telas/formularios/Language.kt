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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.satc.integrador_ai.R
import com.satc.integrador_ai.storage.FormularioViewModel
import com.satc.integrador_ai.telas.exercicios.AppTopBar

@Preview(showBackground = true)
@Composable
fun LanguageSelectionScreenPreview() {
    //LanguageSelectionScreen( onBack = { }, onExit = { }, onNext = { });
}

@Composable
fun LanguageSelectionScreen(
    onBack: () -> Unit,
    onNext: () -> Unit,
    onExit: () -> Unit,
    formularioViewModel: FormularioViewModel
) {
    val idiomas = listOf(
        Pair("Inglês", R.drawable.flag_usa),
        Pair("Espanhol", R.drawable.flag_spain),
        Pair("Italiano", R.drawable.flag_italy),
        Pair("Francês", R.drawable.flag_france),
        Pair("Russo", R.drawable.flag_russia),
        Pair("Chinês", R.drawable.flag_china)
    )

    var selectedLanguage by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            AppTopBar(onExitClick = onExit,onBackClick = onBack, title = "Comece Seu \n Plano de Estudo", showExitButton = false )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.White
            ) {
                Button(
                    onClick = {
                        selectedLanguage?.let {
                            formularioViewModel.setIdioma(selectedLanguage.toString())
                            onNext()
                        }
                    },
                    enabled = selectedLanguage != null,
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
                    text = "Qual idioma deseja estudar?",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(40.dp))

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
            }
        }
    )
}

@Composable
fun LanguageOption(
    name: String,
    flagRes: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        border = BorderStroke(2.dp, if (isSelected) Color(0xFF7061FD) else Color.LightGray),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth(0.3f)
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
                painter = painterResource(id = flagRes),
                contentDescription = name,
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center)
        }
    }
}