package com.satc.integrador_ai.telas.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.satc.integrador_ai.data.LoginDto
import com.satc.integrador_ai.data.login
import com.satc.integrador_ai.storage.PreferencesUserViewModel

// TELA DE LOGIN

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    LoginScreen(navController = navController)
}

@Composable
fun LoginScreen(navController: NavHostController, preferencesUserViewModel: PreferencesUserViewModel = hiltViewModel()) {
    val purple = Color(0xFF7061FD)
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
            text = "Informe seus dados para continuar",
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(100.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Informe seu usuário") },
            placeholder = { Text("usuario123") },
            singleLine = true,
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
            shape = roundedShape,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Informe sua senha") },
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
            onClick = {
                val user = LoginDto(
                    email,
                    password
                )
                login(user, changeScreen = { navController.navigate("home") }, preferencesUserViewModel);
            },
            colors = with(ButtonDefaults) { buttonColors(purple) },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        ) {
            Text("Entrar", fontSize = 16.sp, color = Color.White)
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = { navController.popBackStack() },
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, Color.LightGray),
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        ) {
            Text("Voltar", fontSize = 16.sp, color = Color.Black)
        }
    }
}