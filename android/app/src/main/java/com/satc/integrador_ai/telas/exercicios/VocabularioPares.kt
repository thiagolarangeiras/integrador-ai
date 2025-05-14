package com.satc.integrador_ai.telas.exercicios

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun MatchPairsExerciseScreenPreview(){
    MatchPairsExerciseScreen(
        onNextClick = {},
        onBackClick = {},
        onExitClick = {}
    );
}

@Composable
fun MatchPairsExerciseScreen(
    onBackClick: () -> Unit,
    onExitClick: () -> Unit,
    onNextClick: () -> Unit
) {
    val leftWords = listOf("adeus", "oi", "Brasil", "sim", "chá")
    val rightWords = listOf("Brazil", "tea", "goodbye", "yes", "hi")

    Scaffold (
        topBar = {
            AppTopBar(onExitClick = onExitClick,onBackClick = onBackClick, title = "Exercício\n4 de 8")
        },
        bottomBar = {
            BottomAppBar (
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
                    text = "Vocabulário",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(80.dp))

                Text(
                    text = "Combine os pares:",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column {
                        leftWords.forEach { word ->
                            WordBox(text = word)
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }

                    Spacer(modifier = Modifier.width(32.dp)) // Space between the columns

                    Column {
                        rightWords.forEach { word ->
                            WordBox(text = word)
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }


                Spacer(modifier = Modifier.weight(1f))
            }
        }
    )
}

@Composable
fun WordBox(text: String) {
    Box(
        modifier = Modifier
            .width(140.dp)
            .height(40.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
            .background(Color(0xFFF4F3F3), RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = Color(0xFF7061FD), fontWeight = FontWeight.Medium)
    }
}