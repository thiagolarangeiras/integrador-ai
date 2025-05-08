package com.satc.integrador_ai.telas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.satc.integrador_ai.R

// TELA PERFIL

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PerfilScreenPreview() {
    PerfilScreen()
}

@Composable
fun PerfilScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Perfil",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF7C4DFF)
        )
        Spacer(modifier = Modifier.height(24.dp))

        Image(
            painter = painterResource(id = R.drawable.foto_perfil),
            contentDescription = "Foto de perfil",
            modifier = Modifier
                .size(100.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Ana Souza",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF7C4DFF)
        )
        Text(
            text = "Criciúma",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(64.dp))
        Text(
            text = "ESTATÍSTICAS GERAIS",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.Start).padding(start = 16.dp, end = 16.dp),
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))

        EstatisticaItem(icon = Icons.Default.MenuBook, label = "Planos de Estudo Concluídos", value = "5")
        EstatisticaItem(icon = Icons.Default.CheckCircle, label = "Respostas Corretas", value = "80%")
        EstatisticaItem(icon = Icons.Default.Schedule, label = "Horas Estudadas", value = "45 h")
        EstatisticaItem(icon = Icons.Default.Star, label = "Total de Pontos", value = "1170")

        Spacer(modifier = Modifier.weight(1f))
//        BottomNavigationBar()
    }
}

@Composable
fun EstatisticaItem(icon: ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 16.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF5F5F5))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color(0xFF7C4DFF),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF7C4DFF)
        )
    }
}