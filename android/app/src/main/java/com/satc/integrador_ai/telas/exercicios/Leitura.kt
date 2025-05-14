package com.satc.integrador_ai.telas.exercicios

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun ReadingExerciseScreenPreview(){
    ReadingExerciseScreen(
        onBackClick = {},
        onExitClick = {},
        onNextClick = {}
    );
}

@Composable
fun ReadingExerciseScreen(
    onBackClick: () -> Unit,
    onExitClick: () -> Unit,
    onNextClick: () -> Unit
) {
    val scrollState = rememberScrollState()
    val text = """
        The city, once a place filled with opportunities and life, is now nothing but a dead city. Jobs have gone to automation and the population has moved away. The only people left are those with no options. Susan didn’t know anyone in the city.”
        
        “How should I put myself out there if there is no one?” she thought. The skies started turning grey and wind was sharper. She pulled her scarf more tight and looked nervously at the time. The bus was almost always late. Which was no surprise because all the buses in the dead city were either late or never actually came. 
        
        Suddenly, a woman with children
    """.trimIndent()

    Scaffold(
        topBar = {
            AppTopBar(onExitClick = onExitClick,onBackClick = onBackClick, title = "Exercício\n7 de 8")
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.White
            ) {
                Button(
                    onClick = onNextClick,
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
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(padding)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = "Leitura",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(80.dp))

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(scrollState)
                        .padding(end = 8.dp)
                ) {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyMedium,
                        lineHeight = 24.sp
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Pagination indicator
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(3) { index ->
                        val isSelected = index == 1
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .size(if (isSelected) 10.dp else 8.dp)
                                .clip(CircleShape)
                                .background(if (isSelected) Color(0xFF7061FD) else Color.Gray.copy(alpha = 0.4f))
                        )
                    }
                }
            }
        }
    )
}
