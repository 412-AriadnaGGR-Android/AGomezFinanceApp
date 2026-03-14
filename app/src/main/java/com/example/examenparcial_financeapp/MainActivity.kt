package com.example.examenparcial_financeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.examenparcial_financeapp.ui.theme.ExamenParcialFinanceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExamenParcialFinanceAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFF5F5F5)
                ) {
                    PantallaInicio()
                }
            }
        }
    }
}

//  CLASE DE DATOS DEL USUARIO
data class Usuario(
    val nombre: String,
    val saldo: Double
)

//  DATOS
val miUsuario = Usuario(nombre = "Juan", saldo = 1500.00)

// PANTALLA PRINCIPAL
@Composable
fun PantallaInicio() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(WindowInsets.statusBars.asPaddingValues())
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Encabezado(usuario = miUsuario)
    }
}

//  ENCABEZADO
// Muestra la foto de perfil, el saludo y el ícono de menú
@Composable
fun Encabezado(usuario: Usuario) {
    // Row pone los elementos uno al lado del otro
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween, // uno al inicio, otro al final
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Lado izquierdo: foto + saludo
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Círculo gris con ícono de persona (foto de perfil)
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE0E0E0)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Foto",
                    tint = Color.Gray,
                    modifier = Modifier.size(30.dp)
                )
            }

            // Texto del saludo
            Column {
                Text(
                    text = "Hola ${usuario.nombre}",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Bienvenido",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }

        // Lado derecho: ícono de menú hamburguesa
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Menú",
            modifier = Modifier.size(28.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Vista() {
    ExamenParcialFinanceAppTheme {
        PantallaInicio()
    }
}