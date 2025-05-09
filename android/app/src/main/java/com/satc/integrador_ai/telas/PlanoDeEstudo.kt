package com.satc.integrador_ai.telas

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// TELA PLANO DE ENSINO

@Preview(showBackground = true)
@Composable
fun PlanoDeEstudoPreview() {
    PlanoDeEstudoScreen()
}

@Composable
fun PlanoDeEstudoScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 0.dp)
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Plano de Estudo",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(32.dp))
        DiasDaSemanaSelecionavel(diaSelecionado = 4)

        Spacer(modifier = Modifier.height(40.dp))
        TarefaItem("Prática de Vocabulário", "Estude palavras e frases novas", 0, 10)
        TarefaItem("Presente Simples", "Revisar Tempos Verbais", 0, 20)
        TarefaItem("Compreensão Auditiva", "Exercícios de escuta", 0, 15)

        Spacer(modifier = Modifier.weight(1f))
        BottomNavigationBar()
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
                Text(text = dia, fontSize = 12.sp)
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(
                            if (index + 1 == diaSelecionado) Color(0xFF7C4DFF) else Color(0xFFE0E0E0)
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

@Composable
fun TarefaItem(titulo: String, descricao: String, progresso: Int, total: Int) {

    val progressoPercentual = if (total > 0) progresso.toFloat() / total else 0f

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(1.dp, Color.Black, shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Text(text = titulo, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = descricao, fontSize = 16.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(16.dp))
        LinearProgressIndicator(
            progress = { progressoPercentual.coerceIn(0f, 1f) },
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = Color(0xFFBDBDBD),
            trackColor = Color(0xFFE0E0E0)
        )
        Text(
            text = "$progresso/$total",
            fontSize = 12.sp,
            modifier = Modifier.align(Alignment.End),
            color = Color.Gray
        )
    }
}

@Composable
fun BottomNavigationBar() {
    val items = listOf("Home", "Plano de Estudo", "Chat", "Perfil")
    val icons = listOf(Icons.Default.Home, Icons.Default.CalendarToday, Icons.Default.ChatBubble, Icons.Default.Person)
    var selectedItem by remember { mutableIntStateOf(1) }

    NavigationBar(
        containerColor = Color(0xFFE0E0E0),
        modifier = Modifier.height(72.dp)
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = icons[index],
                        contentDescription = item,
                        modifier = Modifier.size(32.dp).offset(y = (6).dp)
                    )
                },
                label = {
                    Text(text = item, fontSize = 10.sp, modifier = Modifier.offset(y = (-2).dp))
                },
                selected = selectedItem == index,
                onClick = { selectedItem = index },
                alwaysShowLabel = true,
                colors = androidx.compose.material3.NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF7C4DFF),
                    selectedTextColor = Color(0xFF7C4DFF),
                    unselectedIconColor = Color.Black,
                    unselectedTextColor = Color.Black,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}