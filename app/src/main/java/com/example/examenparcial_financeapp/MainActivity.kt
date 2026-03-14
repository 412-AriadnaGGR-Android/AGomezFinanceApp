package com.example.examenparcial_financeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
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

// --- CLASES DE DATOS ---
data class Usuario(
    val nombre: String,
    val saldo: Double
)

data class TarjetaResumen(
    val titulo: String,
    val monto: Double,
    val color: Color
)

// --- DATOS DE PRUEBA ---
val miUsuario = Usuario(nombre = "Juan", saldo = 1500.00)

val misTarjetas = listOf(
    TarjetaResumen("Actividad\nde la Semana", 0.0,   Color(0xFFE0F0E8)), // Verde claro
    TarjetaResumen("Ventas",                  280.99, Color(0xFFF5E6D3)), // Beige
    TarjetaResumen("Ganancias",               280.99, Color(0xFFE8E0F0))  // Lila
)

// --- PANTALLA PRINCIPAL ---
@Composable
fun PantallaInicio() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(WindowInsets.statusBars.asPaddingValues())
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.height(4.dp))
        Encabezado(usuario = miUsuario)
        SeccionTarjetas(tarjetas = misTarjetas)
    }
}

// --- ENCABEZADO ---
@Composable
fun Encabezado(usuario: Usuario) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
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
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Menú",
            modifier = Modifier.size(28.dp)
        )
    }
}

// --- SECCIÓN DE TARJETAS ---
// Una grande a la izquierda, dos pequeñas apiladas a la derecha
@Composable
fun SeccionTarjetas(tarjetas: List<TarjetaResumen>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Tarjeta grande (Actividad)
        TarjetaGrande(tarjeta = tarjetas[0])

        // Columna con dos tarjetas pequeñas
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TarjetaPequena(tarjeta = tarjetas[1], modifier = Modifier.weight(1f))
            TarjetaPequena(tarjeta = tarjetas[2], modifier = Modifier.weight(1f))
        }
    }
}

// Tarjeta grande del lado izquierdo
@Composable
fun TarjetaGrande(tarjeta: TarjetaResumen) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .fillMaxHeight(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = tarjeta.color)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.BarChart,
                contentDescription = "Actividad",
                modifier = Modifier.size(36.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = tarjeta.titulo,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

// Tarjeta pequeña del lado derecho
@Composable
fun TarjetaPequena(tarjeta: TarjetaResumen, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = tarjeta.color)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = tarjeta.titulo, fontSize = 13.sp, color = Color.Gray)
            Text(
                text = "$${tarjeta.monto}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Vista() {
    ExamenParcialFinanceAppTheme {
        PantallaInicio()
    }
}