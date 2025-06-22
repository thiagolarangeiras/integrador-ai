package com.satc.integrador_ai.telas

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.satc.integrador_ai.NavigationTarget

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
                        modifier = Modifier.size(32.dp).offset(y = (16).dp)
                    )
                },
                label = {
                    Text(text = item, fontSize = 10.sp, modifier = Modifier.offset(y = (10).dp))
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