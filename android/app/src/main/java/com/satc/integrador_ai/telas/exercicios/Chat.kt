package com.satc.integrador_ai.telas.exercicios

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ChatScreen(navController: NavController) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        AppTopBar(onBackClick = {}, title = "Chat", showBackIcon = false, navController = navController )
        ChatHeader()
        Spacer(modifier = Modifier.height(8.dp))
        ChatMessages(modifier = Modifier.weight(1f))
        ChatInput()
    }
}

@Composable
fun ChatHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF7061FD))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE8E9EB)),
                contentAlignment = Alignment.Center
            ) {
                Text("IA", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 24.sp)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("Inteligência Artificial", color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
        }
    }
}


@Composable
fun ChatMessages(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        MessageBubble("Shall we go to the cinema today?", isUser = true)
        MessageBubble("Let's see Iron", isUser = false)
        MessageBubble("OMG! Yes, That's great idea!!!!", isUser = true)
    }
}

@Composable
fun MessageBubble(message: String, isUser: Boolean) {
    val bubbleColor = if (isUser) Color(0xFF7061FD) else Color(0xFFE8E9EB)
    val textColor = if (isUser) Color.White else Color.Black
    val alignment = if (isUser) Alignment.End else Alignment.Start

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = alignment
    ) {
        Box(
            modifier = Modifier
                .padding(top = 8.dp)
                .background(bubbleColor, RoundedCornerShape(16.dp))
                .padding(12.dp)
        ) {
            Text(text = message, color = textColor, fontSize = 14.sp)
        }
    }
}

@Composable
fun ChatInput() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp) // size of background circle
                .clip(CircleShape)
                .background(Color(0xFFE8E9EB)), // background color
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                tint = Color.Black // icon color
            )
        }
        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text(
                text = "Write a message",
                fontSize = 14.sp,
                color = Color.Gray
            ) },
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF0F0F0),
                unfocusedContainerColor = Color(0xFFF0F0F0),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(16.dp),
            trailingIcon = {
                IconButton(onClick = { /* handle mic click */ }) {
                    Icon(
                        imageVector = Icons.Default.Mic,
                        contentDescription = "Mic",
                        tint = Color.Black // optional: change icon color
                    )
                }
            }
        )

    }
}