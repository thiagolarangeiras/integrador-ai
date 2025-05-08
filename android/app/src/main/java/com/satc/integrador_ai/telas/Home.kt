package com.satc.integrador_ai.telas

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

// TELA HOME

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

@Composable
fun HomeScreenPreview(navController: NavHostController) {
    HomeScreen()
}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Olá, Estudante!",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))
        CardSection(
            title = "Inglês Intermediário",
            subtitle = "42% concluído",
            buttonText = "Ir para Plano de Estudo"
        )

        Spacer(modifier = Modifier.height(16.dp))
        EstudoHojeCard()

        Spacer(modifier = Modifier.height(16.dp))
        ProgressoCard()

        Spacer(modifier = Modifier.weight(1f))
//        BottomNavigationBar()
    }
}

@Composable
fun CardSection(title: String, subtitle: String, buttonText: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .border(1.dp, Color.Black, shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Text(text = title, fontWeight = FontWeight.Bold, fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 4.dp))
        Text(text = subtitle, fontSize = 16.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /* TODO */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7C4DFF)),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = buttonText, color = Color.White)
        }
    }
}

@Composable
fun EstudoHojeCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .border(1.dp, Color.Black, shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Text(text = "Estudo de Hoje", fontWeight = FontWeight.Bold, fontSize = 20.sp,
            modifier = Modifier
                .padding(bottom = 4.dp))
        Text(text = "Simple Present", fontSize = 16.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            LinearProgressIndicator(
                progress = { 0f },
                modifier = Modifier
                    .weight(1f)
                    .height(10.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = Color(0xFFBDBDBD),
                trackColor = Color(0xFFE0E0E0),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "30 min", fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /* TODO */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7C4DFF)),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Começar Agora", color = Color.White)
        }
    }
}

@Composable
fun ProgressoCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .border(1.dp, Color.Black, shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Text(text = "Seu Progresso", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            ProgressoItem(icon = Icons.Default.Translate, label = "Vocabulário", progresso = 0)
            ProgressoItem(icon = Icons.Default.List, label = "Gramática", progresso = 0)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            ProgressoItem(icon = Icons.Default.Headphones, label = "Audição", progresso = 0)
            ProgressoItem(icon = Icons.Default.Mic, label = "Fala", progresso = 100)
        }
    }
}

@Composable
fun ProgressoItem(icon: ImageVector, label: String, progresso: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(40.dp)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(text = label, fontSize = 16.sp)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                LinearProgressIndicator(
                    progress = { progresso / 100f },
                    modifier = Modifier
                        .width(60.dp)
                        .height(10.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = if (progresso == 100) Color(0xFF7C4DFF) else Color(0xFFBDBDBD),
                    trackColor = Color(0xFFE0E0E0),
                )
                Text(text = "$progresso%", fontSize = 12.sp)
            }
        }
    }
}