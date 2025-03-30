package com.example.colormixer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Se establece el contenido de la actividad usando Jetpack Compose
        setContent {
            MaterialTheme {
                // Llama a la función composable principal de la aplicación
                ColorMixerApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class) // Se Opta por usar APIs experimentales de Material3
@Composable
fun ColorMixerApp() {
    // Declaración de estados para los valores RGB, inicializados con valores predeterminados
    var redValue by remember { mutableStateOf(223f) }
    var greenValue by remember { mutableStateOf(72f) }
    var blueValue by remember { mutableStateOf(73f) }

    // Se calcula el color mezclado normalizando los valores (0..255) a (0..1)
    val mixedColor = Color(
        red = redValue / 255f,
        green = greenValue / 255f,
        blue = blueValue / 255f
    )

    // Scaffold se usa para estructurar la interfaz con una TopAppBar y el contenido principal
    Scaffold(
        topBar = {
            // Barra superior que contiene un icono y el título "Color Mixer"
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Icono de la aplicación, usando el icono "ColorLens" de Material Icons
                        Icon(
                            imageVector = Icons.Filled.ColorLens,
                            contentDescription = "Color Icon",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        // Título de la aplicación en la barra superior
                        Text(text = "Color Mixer")
                    }
                }
            )
        },
        content = { innerPadding ->
            // Contenedor principal con relleno para evitar solapamiento con la TopAppBar
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                // Área de previsualización que muestra el color resultante en tiempo real
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(mixedColor),
                    contentAlignment = Alignment.Center
                ) {
                    // Muestra los valores RGB actuales en el centro del área de previsualización
                    Text(
                        text = "R: ${redValue.toInt()}  G: ${greenValue.toInt()}  B: ${blueValue.toInt()}",
                        color = Color.White,
                        fontSize = 20.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Slider para ajustar el valor del componente Rojo
                Text(text = "Red", fontSize = 18.sp)
                Slider(
                    value = redValue,
                    onValueChange = { newValue -> redValue = newValue },
                    valueRange = 0f..255f,
                    colors = SliderDefaults.colors(
                        thumbColor = Color.Red,
                        activeTrackColor = Color.Red
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Slider para ajustar el valor del componente Verde
                Text(text = "Green", fontSize = 18.sp)
                Slider(
                    value = greenValue,
                    onValueChange = { newValue -> greenValue = newValue },
                    valueRange = 0f..255f,
                    colors = SliderDefaults.colors(
                        thumbColor = Color.Green,
                        activeTrackColor = Color.Green
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Slider para ajustar el valor del componente Azul
                Text(text = "Blue", fontSize = 18.sp)
                Slider(
                    value = blueValue,
                    onValueChange = { newValue -> blueValue = newValue },
                    valueRange = 0f..255f,
                    colors = SliderDefaults.colors(
                        thumbColor = Color.Blue,
                        activeTrackColor = Color.Blue
                    )
                )
            }
        }
    )
}
