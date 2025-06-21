package com.satc.integrador_ai.telas.exercicios

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import com.satc.integrador_ai.storage.ExercicioViewModel

@Preview
@Composable
fun MatchPairsExerciseScreenPreview(){
//    MatchPairsExerciseScreen();
}

@Composable
fun MatchPairsExerciseScreen(exercicioViewModel: ExercicioViewModel, navController: NavController) {
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        exercicioViewModel.navigationEvent.collect { state ->
            navController.navigate(state)
        }
    }

    val optionsEsquerda = remember {
        exercicioViewModel.getListaEsquerda().shuffled()
    }
    val optionsDireita = remember {
        exercicioViewModel.getListaDireita().shuffled()
    }

    Scaffold (
        topBar = {
            AppTopBar(onBackClick = {}, title = exercicioViewModel.getTitle(), showBackIcon = false, navController = navController )
        },
        bottomBar = {
            BottomAppBar (
                containerColor = Color.White
            ) {
                Button(
                    onClick = { exercicioViewModel.onNextScreen() },
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
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(80.dp))

                Text(
                    text = "Combine os pares:",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        optionsEsquerda.forEach { word ->
                            OptionButton(
                                option = word,
                                onClick = {exercicioViewModel.addKeyRespostaFeitaVocabularioPares(word)},
                                isLeftValue = true,
                                exercicioViewModel
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(32.dp)) // Space between the columns

                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        optionsDireita.forEach { word ->
                            OptionButton(
                                option = word,
                                onClick = {exercicioViewModel.addValueRespostaFeitaVocabularioPares(word)},
                                isLeftValue = false,
                                exercicioViewModel
                            )
                        }
                    }
                }


                Spacer(modifier = Modifier.weight(1f))
            }
        }
    )
}

@Composable
fun OptionButton(option: String, onClick: () -> Unit, isLeftValue: Boolean, exercicioViewModel: ExercicioViewModel) {
//    || (!isLeftValue && !exercicioViewModel.hasAnyValueSelected())
    Button(
        onClick = onClick,
//        enabled = (isLeftValue && !exercicioViewModel.hasAnyKeySelected() || (exercicioViewModel.verifyRespostaListaEsquerda(option) && !exercicioViewModel.hasAnyKeySelected())) || (!isLeftValue && !exercicioViewModel.hasAnyValueSelected() || exercicioViewModel.verifyRespostaListaDireita(option)),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if ((isLeftValue && exercicioViewModel.verifyRespostaListaEsquerda(option)) ||
                (!isLeftValue && exercicioViewModel.verifyRespostaListaDireita(option))) Color(0xFFEDE9FD) else Color.White,
            contentColor = Color(0xFF7061FD)
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        border = BorderStroke(1.dp, Color.Gray),
        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 12.dp),
    ) {
        Text(
            text = option,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            maxLines = 2,
            softWrap = true
        )
    }
}

//@Composable
//fun WordBox(text: String) {
//    Box(
//        modifier = Modifier
//            .width(140.dp)
//            .height(40.dp)
//            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
//            .background(Color(0xFFF4F3F3), RoundedCornerShape(8.dp)),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(text = text, color = Color(0xFF7061FD), fontWeight = FontWeight.Medium)
//    }
//}