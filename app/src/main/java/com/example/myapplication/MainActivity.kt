package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                TwitchApp()
            }
        }
    }
}

@Composable
fun TwitchApp() {
    var telaAtual by rememberSaveable { mutableStateOf("home") } // home, profile, search

    when (telaAtual) {
        "home" -> TwitchHomeScreen(
            onClickPerfil = { telaAtual = "profile" },
            onClickPesquisar = { telaAtual = "search" }
        )
        "profile" -> TwitchProfileScreen(
            onClickVoltar = { telaAtual = "home" },
            onClickPesquisar = { telaAtual = "search" }
        )
        "search" -> TwitchSearchScreen(
            onClickHome = { telaAtual = "home" },
            onClickPerfil = { telaAtual = "profile" }
        )
    }
}

// ----------------- HOME -----------------
@Composable
fun TwitchHomeScreen(onClickPerfil: () -> Unit, onClickPesquisar: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Topo: perfis
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                val nomes = listOf("Streamer1", "Streamer2", "Streamer3", "Streamer4")
                nomes.forEach { nome ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                                .border(3.dp, Color.White, CircleShape)
                                .background(Color.Gray)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(nome, color = Color.White, fontSize = 12.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Retângulo da live
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color(0xFF2C2C2C))
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth(0.9f)
                        .aspectRatio(16f / 9f)
                        .background(Color.DarkGray)
                )

                // "Seguindo"
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 8.dp, top = 16.dp)
                        .background(Color(0x66AAAAAA), shape = CircleShape)
                        .padding(6.dp)
                ) {
                    Text("❤", fontSize = 16.sp, color = Color.White)
                    Text("Seguindo", fontSize = 12.sp, color = Color.White)
                }

                // Ao vivo / Clipes
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 16.dp)
                        .fillMaxWidth(0.5f)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Ao vivo", fontSize = 18.sp, color = Color.White)
                        Spacer(modifier = Modifier.height(4.dp))
                        Box(
                            modifier = Modifier
                                .height(2.dp)
                                .width(40.dp)
                                .background(Color.White)
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Clipes", fontSize = 18.sp, color = Color.White)
                        Spacer(modifier = Modifier.height(4.dp))
                        Box(
                            modifier = Modifier
                                .height(2.dp)
                                .width(40.dp)
                                .background(Color.Transparent)
                        )
                    }
                }

                // Ícones laterais
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 8.dp, bottom = 16.dp)
                ) {
                    val iconesLaterais = listOf(
                        "\u25CF", "\u2764", "\uD83D\uDD17", "\uD83D\uDD07", "\u22EE"
                    )
                    iconesLaterais.forEachIndexed { index, icon ->
                        Text(
                            text = icon,
                            fontSize = if (index == 0) 72.sp else 24.sp,
                            color = Color.White
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(80.dp))
        }

        // Rodapé
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .align(Alignment.BottomCenter)
                .background(Color(0xFF111111))
                .padding(horizontal = 16.dp)
        ) {
            val icones = listOf("\u2302", "\uD83D\uDD0D", "+", "\uD83D\uDD14", "\u25CF")
            icones.forEachIndexed { index, icon ->
                Text(
                    text = icon,
                    fontSize = if (index == 4) 48.sp else 24.sp,
                    color = Color.White,
                    modifier = when (index) {
                        1 -> Modifier.clickable { onClickPesquisar() } // lupa
                        4 -> Modifier.clickable { onClickPerfil() } // perfil
                        else -> Modifier
                    }
                )
            }
        }
    }
}

// ----------------- PROFILE -----------------
@Composable
fun TwitchProfileScreen(onClickVoltar: () -> Unit, onClickPesquisar: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Topo roxo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Color(0xFF9147FF))
            )

            // Perfil
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.Top
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .offset(y = (-24).dp)
                            .size(96.dp)
                            .clip(CircleShape)
                            .background(Color.Gray)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("12.3k seguidores", color = Color.White, fontSize = 12.sp)
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(verticalArrangement = Arrangement.Center) {
                    Text("NomeDoStreamer", color = Color.White, fontSize = 24.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Última vez ao vivo: 2h atrás", color = Color.Gray, fontSize = 14.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Opções
            val opcoes = listOf(
                "Meu canal" to "\uD83D\uDC64",
                "Painel do criador" to "\uD83D\uDCCB",
                "Inscrições" to "\u2B50",
                "Drops" to "\uD83C\uDF81",
                "Twitch Turbo" to "\uD83D\uDD0B",
                "Configurações da conta" to "\u2699"
            )

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
            ) {
                opcoes.forEach { (texto, icone) ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp)
                    ) {
                        Text(icone, fontSize = 24.sp, color = Color.White)
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(texto, fontSize = 18.sp, color = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))
        }

        // Rodapé
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .align(Alignment.BottomCenter)
                .background(Color(0xFF111111))
                .padding(horizontal = 16.dp)
        ) {
            val icones = listOf("\u2302", "\uD83D\uDD0D", "+", "\uD83D\uDD14", "\u25CF")
            icones.forEachIndexed { index, icon ->
                Text(
                    text = icon,
                    fontSize = if (index == 4) 48.sp else 24.sp,
                    color = Color.White,
                    modifier = when (index) {
                        0 -> Modifier.clickable { onClickVoltar() } // casa
                        1 -> Modifier.clickable { onClickPesquisar() } // lupa
                        else -> Modifier
                    }
                )
            }
        }
    }
}

// ----------------- SEARCH -----------------
@Composable
fun TwitchSearchScreen(onClickHome: () -> Unit, onClickPerfil: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Barra de pesquisa
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.DarkGray, shape = CircleShape)
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text("🔍", fontSize = 20.sp, color = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Buscar canais, jogos e clipes", color = Color.Gray)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Pesquisas recentes
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                val pesquisas = listOf("Valorant", "League of Legends", "Minecraft")
                pesquisas.forEach { pesquisa ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("🔄", fontSize = 16.sp, color = Color.White)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(pesquisa, color = Color.White)
                        }
                        Text("❌", fontSize = 16.sp, color = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Categorias / Canais ao vivo com filtro
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("≡", fontSize = 20.sp, color = Color.White) // filtro
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Categorias", color = Color.White)
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Canais ao vivo", color = Color.White)
                    Spacer(modifier = Modifier.width(4.dp))
                    Box(
                        modifier = Modifier
                            .height(2.dp)
                            .width(40.dp)
                            .background(Color.White)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Grid de categorias (6 retângulos, 3x2)
            val categorias = listOf("FPS", "MOBA", "RPG", "Aventura", "Simulação", "Luta")
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                categorias.chunked(3).forEach { linha ->
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        linha.forEach { categoria ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.weight(1f)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .aspectRatio(3f / 5f)
                                        .background(Color.DarkGray)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(categoria, color = Color.White)
                                Spacer(modifier = Modifier.height(4.dp))
                                // Info de espectadores e tag
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(8.dp)
                                            .background(Color.Red, shape = CircleShape)
                                    )
                                    Text("12.3k", color = Color.Gray, fontSize = 12.sp)
                                }
                                Text("FPS", color = Color.Gray, fontSize = 12.sp)
                            }
                        }
                        if (linha.size < 3) {
                            repeat(3 - linha.size) { Spacer(modifier = Modifier.weight(1f)) }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(80.dp))
        }

        // Rodapé fixo
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .align(Alignment.BottomCenter)
                .background(Color(0xFF111111))
                .padding(horizontal = 16.dp)
        ) {
            val icones = listOf("\u2302", "\uD83D\uDD0D", "+", "\uD83D\uDD14", "\u25CF")
            icones.forEachIndexed { index, icon ->
                Text(
                    text = icon,
                    fontSize = if (index == 4) 48.sp else 24.sp,
                    color = Color.White,
                    modifier = when (index) {
                        0 -> Modifier.clickable { onClickHome() } // casa
                        4 -> Modifier.clickable { onClickPerfil() } // perfil
                        else -> Modifier
                    }
                )
            }
        }
    }
}
