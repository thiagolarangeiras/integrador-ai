 package com.satc.integrador_ai.telas.exercicios

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun AudioExerciseScreenPreview() {
    AudioExerciseScreen()
}

@Composable
fun AudioExerciseScreen() {
    var answer by remember { mutableStateOf("Where are you from ?") }

    Scaffold(
        topBar = {
            AppTopBar(onExitClick = { /* handle exit */ },onBackClick = { /* handle back*/ }, "Exercício\n1 de 8")
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.White
            ) {
                Button(
                    onClick = { /* TODO: Handle next */ },
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
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color.White)
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = "Compreensão\n Auditiva",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(80.dp))

                // Audio Player Fake UI
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF7061FD)),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "",
                            fontSize = 24.sp,
                            color = Color.White
                        )

                        IconButton(onClick = { /* TODO: play audio */ }) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = "Play",
                                tint = Color.White,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    "Escreva o que escutar:",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = answer,
                    onValueChange = { answer = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0xFFF4F3F3),
                    )
                )

                Spacer(modifier = Modifier.height(64.dp))
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    onExitClick: () -> Unit,
    onBackClick: () -> Unit,
    title: String,
    showBackIcon: Boolean = true,
    showExitButton: Boolean = true
) {
    CenterAlignedTopAppBar(
        title = {
                Box(
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp
                )
            }
        },
        navigationIcon = if (showBackIcon) {
            {
                IconButton(
                    onClick = onBackClick,

                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Voltar",
                        tint = Color.Black,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        } else {
            {}
        },
        actions = {
            if (showExitButton) {
                Box(
                    modifier = Modifier.fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    TextButton(onClick = onExitClick) {
                        Text(
                            text = "Sair",
                            color = Color(0xFF7061FD),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFFF4F3F3)
        ),
        modifier = Modifier.fillMaxWidth()
    )
}