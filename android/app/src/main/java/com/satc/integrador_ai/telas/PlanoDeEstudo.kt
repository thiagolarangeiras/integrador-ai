package com.satc.integrador_ai.telas

import  androidx.compose.foundation.background
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
import androidx.navigation.NavController
import com.satc.integrador_ai.NavigationTarget

@Preview(showBackground = true)
@Composable
fun PlanoDeEstudoPreview() {
    //PlanoDeEstudoScreen()
}

@Composable
fun PlanoDeEstudoScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 0.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Plano de Estudo",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(40.dp))
        DiasDaSemanaSelecionavel(diaSelecionado = 4)

        Spacer(modifier = Modifier.height(40.dp))
        TarefaItem("Prática de Vocabulário", "Estude palavras e frases novas", 0, 10)
        TarefaItem("Presente Simples", "Revisar Tempos Verbais", 0, 20)
        TarefaItem("Compreensão Auditiva", "Exercícios de escuta", 0, 15)
        TarefaItem("Leitura de Textos", "Ler artigos e histórias curtas", 0, 12)

        Spacer(modifier = Modifier.weight(1f))
        BottomNavigationBar(1, navController)
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

@Composable
fun TarefaItem(titulo: String, descricao: String, progresso: Int, total: Int) {

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
            color = Color(0xFFBDBDBD),
            trackColor = Color(0xFFE0E0E0)
        )

    }
}

@Composable
fun BottomNavigationBar(index: Int, navController: NavController) {
    val items = listOf("Home", "Plano de Estudo", "Chat", "Perfil")
    val icons = listOf(Icons.Default.Home, Icons.Default.CalendarToday, Icons.Default.ChatBubble, Icons.Default.Person)
    var selectedItem by remember { mutableIntStateOf(index) }

    NavigationBar(
        containerColor = Color(0xFFF4F3F3),
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
                onClick = {
                    selectedItem = index
                    when (index) {
                        0 -> navController.navigate(NavigationTarget.Home.route)
                        1 -> navController.navigate(NavigationTarget.PlanoDeEstudo.route)
                        2 -> navController.navigate(NavigationTarget.Chat.route)
                        3 -> navController.navigate(NavigationTarget.Perfil.route)
                        else -> println("Valor desconhecido")
                    }
                          },
                alwaysShowLabel = true,
                colors = androidx.compose.material3.NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF7061FD),
                    selectedTextColor = Color(0xFF7061FD),
                    unselectedIconColor = Color.Black,
                    unselectedTextColor = Color.Black,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}