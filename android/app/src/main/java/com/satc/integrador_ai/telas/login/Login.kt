<<<<<<< Updated upstream:android/app/src/main/java/com/satc/integrador_ai/telas/Login.kt
package com.satc.integrador_ai.telas
=======
package com.example.teste_telas_ia
>>>>>>> Stashed changes:android/app/src/main/java/com/satc/integrador_ai/telas/login/Login.kt

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

// TELA DE LOGIN

@Composable
fun LoginScreen(navController: NavHostController) {
    val purple = Color(0xFF7B61FF)
    val roundedShape = RoundedCornerShape(12.dp)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Entrar",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Digite seus dados para continuar",
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(100.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Digite seu e-mail") },
            placeholder = { Text("email@email.com") },
            singleLine = true,
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
            shape = roundedShape,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Digite sua senha") },
            placeholder = { Text("********") },
            singleLine = true,
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Default.Visibility
                else Icons.Default.VisibilityOff

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = null)
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            shape = roundedShape,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = rememberMe,
                onCheckedChange = { rememberMe = it },
                colors = CheckboxDefaults.colors(checkedColor = purple)
            )

            Text(
                text = "Lembrar-me",
                modifier = Modifier.weight(1f),
                color = Color.Black
            )

            TextButton(onClick = { /* TODO: ação de recuperar senha */ }) {
                Text("Recuperar senha", color = purple)
            }
        }

        Spacer(modifier = Modifier.height(90.dp))

        Button(
<<<<<<< Updated upstream:android/app/src/main/java/com/satc/integrador_ai/telas/Login.kt
            onClick = { /* TODO: ação de login */ },
            colors = ButtonDefaults.buttonColors(purple),
            shape = RoundedCornerShape(25.dp),
=======
            onClick = { navController.navigate("login") },
            colors = with(ButtonDefaults) { buttonColors(purple) },
            shape = RoundedCornerShape(15.dp),
>>>>>>> Stashed changes:android/app/src/main/java/com/satc/integrador_ai/telas/login/Login.kt
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Entrar", fontSize = 16.sp, color = Color.White)
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun LoginScreenPreview() {
//    LoginScreen()
//}