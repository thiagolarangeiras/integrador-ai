package com.satc.integrador_ai.telas

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.satc.integrador_ai.NavigationTarget
import com.satc.integrador_ai.R
import com.satc.integrador_ai.storage.HomeViewModel
import com.satc.integrador_ai.storage.PreferencesUserViewModel

@Preview(showBackground = true)
@Composable
fun PerfilScreenPreview(
    mainViewModel: HomeViewModel = hiltViewModel(),
    preferencesUserViewModel: PreferencesUserViewModel = hiltViewModel(),
) {
    val navController = rememberNavController()
    PerfilScreen(mainViewModel, preferencesUserViewModel, navController)
}

@Composable
fun PerfilScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    preferencesUserViewModel: PreferencesUserViewModel = hiltViewModel(),
    navController: NavController
) {

    homeViewModel.loadUserData()
    val usuario by homeViewModel.usuario.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Perfil",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF7061FD)
        )
        Spacer(modifier = Modifier.height(20.dp))

        Image(
            painter = painterResource(id = R.drawable.profile_user),
            contentDescription = "Foto de perfil",
            modifier = Modifier
                .size(100.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "${usuario.nomeCompleto}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF7061FD)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(
            onClick = { navController.navigate(NavigationTarget.Welcome.route)
                        preferencesUserViewModel.logOut()
                      },
            contentPadding = PaddingValues(horizontal = 4.dp),
            modifier = Modifier.height(26.dp),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color(0xFF7061FD)
            ),
            border = BorderStroke(1.dp, Color(0xFF7061FD))
        ) {
            Text(text = "Sair")
        }

        Spacer(modifier = Modifier.height(64.dp))
        Text(
            text = "Estatísticas Gerais",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.Start).padding(start = 16.dp, end = 16.dp),
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))

        EstatisticaItem(icon = Icons.Default.MenuBook, label = "Planos de Estudo Concluídos", value = "5")
        EstatisticaItem(icon = Icons.Default.CheckCircle, label = "Respostas Corretas", value = "80%")
        EstatisticaItem(icon = Icons.Default.Schedule, label = "Horas Estudadas", value = "45 h")
        EstatisticaItem(icon = Icons.Default.Star, label = "Total de Pontos", value = "1170")

        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .padding(16.dp)
        ) {
            Button(
                onClick = { navController.navigate(NavigationTarget.Language.route) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7061FD)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Redefinir Preferências", color = Color.White)
            }
        }

        BottomNavigationBar(3, navController)
    }
}

@Composable
fun EstatisticaItem(icon: ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 16.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF5F5F5))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color(0xFF7C4DFF),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF7C4DFF)
        )
    }
}
