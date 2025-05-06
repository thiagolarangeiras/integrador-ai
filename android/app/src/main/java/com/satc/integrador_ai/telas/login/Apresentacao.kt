<<<<<<< Updated upstream:android/app/src/main/java/com/satc/integrador_ai/telas/Apresentacao.kt
package com.satc.integrador_ai.telas
=======
package com.example.teste_telas_ia
>>>>>>> Stashed changes:android/app/src/main/java/com/satc/integrador_ai/telas/login/Apresentacao.kt

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.navigation.NavController

// TELA DE APRESENTAÇÃO

@Composable
fun TalkAiWelcomeScreen(navController: NavController) {
    val purple = Color(0xFF7B61FF)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 32.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Ícone circular com ícone de chat
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(purple, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Chat,
                contentDescription = "Ícone",
                tint = Color.White,
                modifier = Modifier.size(48.dp)
            )
        }

<<<<<<< Updated upstream:android/app/src/main/java/com/satc/integrador_ai/telas/Apresentacao.kt
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Talk.ai",
            fontSize = 32.sp,
=======
        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Talk.ai",
            fontSize = 36.sp,
>>>>>>> Stashed changes:android/app/src/main/java/com/satc/integrador_ai/telas/login/Apresentacao.kt
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

<<<<<<< Updated upstream:android/app/src/main/java/com/satc/integrador_ai/telas/Apresentacao.kt
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Aprenda idiomas\nde forma personalizada",
            fontSize = 18.sp,
            color = Color.DarkGray,
=======
        Spacer(modifier = Modifier.height(72.dp))

        Text(
            text = "Aprenda idiomas\nde forma personalizada",
            fontSize = 20.sp,
            color = Color.DarkGray,
            fontWeight = FontWeight.Bold,
>>>>>>> Stashed changes:android/app/src/main/java/com/satc/integrador_ai/telas/login/Apresentacao.kt
            textAlign = TextAlign.Center,
            lineHeight = 26.sp
        )

<<<<<<< Updated upstream:android/app/src/main/java/com/satc/integrador_ai/telas/Apresentacao.kt
        Spacer(modifier = Modifier.height(48.dp))

        // Botão de cadastro
        Button (
            onClick = { /* TODO: ação de cadastro */ },
            colors = ButtonDefaults.buttonColors(purple),
            shape = RoundedCornerShape(25.dp),
=======
        Spacer(modifier = Modifier.height(100.dp))

        // Botão de cadastro
        Button(
            onClick = { navController.navigate("signup") },
            colors = ButtonDefaults.buttonColors(containerColor = purple),
            shape = RoundedCornerShape(15.dp),
>>>>>>> Stashed changes:android/app/src/main/java/com/satc/integrador_ai/telas/login/Apresentacao.kt
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Cadastre-se", fontSize = 16.sp, color = Color.White)
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Botão de login
<<<<<<< Updated upstream:android/app/src/main/java/com/satc/integrador_ai/telas/Apresentacao.kt
        OutlinedButton (
            onClick = { /* TODO: ação de login */ },
            shape = RoundedCornerShape(25.dp),
=======
        OutlinedButton(
            onClick = { navController.navigate("login") },
            shape = RoundedCornerShape(15.dp),
>>>>>>> Stashed changes:android/app/src/main/java/com/satc/integrador_ai/telas/login/Apresentacao.kt
            border = BorderStroke(1.dp, Color.LightGray),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Entrar", fontSize = 16.sp, color = Color.Black)
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun TalkAiWelcomeScreenPreview() {
//    TalkAiWelcomeScreen()
//}