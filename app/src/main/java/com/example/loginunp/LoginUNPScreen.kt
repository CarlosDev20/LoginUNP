package com.example.loginunp

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loginunp.components.FormularioLogin
import com.example.loginunp.components.HeaderUNP
import com.example.loginunp.ui.theme.LoginUNPTheme

@Composable
fun LoginUNPScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var mostrarDialogo by remember { mutableStateOf(false) }
    var nombreUsuario by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Parte superior: Header UNP con Logo
        HeaderUNP()

        // Parte central: Formulario centrado
        FormularioLogin(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            onLoginClick = { usuario ->
                val nombreValido = usuario.ifBlank { "Usuario" }
                nombreUsuario = nombreValido

                // Muestra un mensaje emergente Toast
                /*
                Toast.makeText(
                    context,
                    "¡Hola, $nombreValido! Bienvenido/a a la UNP",
                    Toast.LENGTH_LONG
                ).show()
                */

                // También activa el diálogo emergente para mayor visibilidad
                mostrarDialogo = true
            }
        )
    }

    // Diálogo emergente
    if (mostrarDialogo) {
        AlertDialog(
            onDismissRequest = { mostrarDialogo = false },
            title = { Text(text = "¡Saludo!") },
            text = { Text(text = "¡Hola, $nombreUsuario! Bienvenido/a a la Universidad Nacional de Piura.") },
            confirmButton = {
                TextButton(onClick = { mostrarDialogo = false }) {
                    Text("Aceptar")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginUNPScreenPreview() {
    LoginUNPTheme {
        LoginUNPScreen()
    }
}