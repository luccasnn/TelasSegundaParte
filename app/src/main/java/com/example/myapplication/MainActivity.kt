package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.painterResource
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
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

@Preview

@Composable
fun TwitchApp() {
    var telaAtual by rememberSaveable { mutableStateOf("home") }

    when (telaAtual) {
        "home" -> TwitchHomeScreen(
            onClickPerfil = { telaAtual = "profile" },
            onClickPesquisar = { telaAtual = "search" }
        )

        "profile" -> TwitchProfileScreen(
            onClickVoltar = { telaAtual = "home" },
            onClickPesquisar = { telaAtual = "search" },
            onClickPerfil = { telaAtual = "profile" }
        )

        "search" -> TwitchSearchScreen(
            onClickHome = { telaAtual = "home" },
            onClickPerfil = { telaAtual = "profile" }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        TwitchApp()
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
            // Topo: perfis com imagens diferentes
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                val nomes = listOf("Gaules", "Asmongold", "BaianoTV", "Alanzoka")
                val imagens = listOf(
                    R.drawable.streamer1,
                    R.drawable.streamer2,
                    R.drawable.streamer3,
                    R.drawable.streamer4
                )
                imagens.forEachIndexed { index, imagemResId ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = imagemResId),
                            contentDescription = "Perfil do ${nomes[index]}",
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                                .border(3.dp, Color.White, CircleShape)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(nomes[index], color = Color.White, fontSize = 12.sp)
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
                Image(
                    painter = painterResource(id = R.drawable.telalive), // substitua "telalive" pelo nome correto do arquivo da imagem
                    contentDescription = "Telalive",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth()
                        .aspectRatio(16f / 9f)
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
            val icones = listOf("\u2302", "\uD83D\uDD0D", "+", "\uD83D\uDD14")
            icones.forEachIndexed { index, icon ->
                Text(
                    text = icon,
                    fontSize = 24.sp,
                    color = Color.White,
                    modifier = when (index) {
                        1 -> Modifier.clickable { onClickPesquisar() } // lupa
                        else -> Modifier
                    }
                )
            }

            // Imagem do perfil no lugar do círculo
            Image(
                painter = painterResource(id = R.drawable.fotoperfil),
                contentDescription = "Foto do Perfil",
                contentScale = ContentScale.Crop,  // Isso faz a imagem "cortar" e preencher o espaço
                modifier = Modifier
                    .size(34.dp) // tamanho do círculo
                    .clip(CircleShape)
                    .border(width = 2.dp, color = Color.Gray, shape = CircleShape) // borda fina
                    .clickable { onClickPerfil() }
            )
        }
    }
}




// ----------------- PROFILE -----------------

@Composable
fun TwitchProfileScreen(
    onClickVoltar: () -> Unit,
    onClickPesquisar: () -> Unit,
    onClickPerfil: () -> Unit
) {
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
                    Image(
                        painter = painterResource(id = R.drawable.fotoperfil),
                        contentDescription = "Foto do Perfil",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .offset(y = (-24).dp)
                            .size(96.dp)
                            .clip(CircleShape)
                            .border(width = 2.dp, color = Color.Gray, shape = CircleShape)
                    )

                    Spacer(modifier = Modifier.height(4.dp))
                    Text("12.3k seguidores", color = Color.White, fontSize = 12.sp)
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(verticalArrangement = Arrangement.Center) {
                    Text("Meu Perfil", color = Color.White, fontSize = 24.sp)
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

        // rodape igual home
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
            val icones = listOf("\u2302", "\uD83D\uDD0D", "+", "\uD83D\uDD14")
            icones.forEachIndexed { index, icon ->
                Text(
                    text = icon,
                    fontSize = 24.sp,
                    color = Color.White,
                    modifier = when (index) {
                        0 -> Modifier.clickable { onClickVoltar() }
                        1 -> Modifier.clickable { onClickPesquisar() }
                        else -> Modifier
                    }
                )
            }

            // imagem do perfil
            Image(
                painter = painterResource(id = R.drawable.fotoperfil),
                contentDescription = "Foto do Perfil",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .border(width = 2.dp, color = Color.Gray, shape = CircleShape)
                    .clickable { onClickPerfil() }
            )
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
            // Categorias com imagens
            val categoriasComImagens = listOf(
                Triple("League of Legends", R.drawable.lol, "MOBA"),
                Triple("CS2", R.drawable.cs2, "FPS"),
                Triple("Dota 2", R.drawable.dota, "MOBA"),
                Triple("Valorant", R.drawable.valorant, "FPS"),
                Triple("GTA V", R.drawable.gta, "Aventura"),
                Triple("IRL", R.drawable.irl, "Vida Real")
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                categoriasComImagens.chunked(3).forEach { linha ->
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        linha.forEach { (nome, imagemRes, tipo) ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.weight(1f)
                            ) {
                                Image(
                                    painter = painterResource(id = imagemRes),
                                    contentDescription = "Categoria $nome",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .aspectRatio(3f / 5f)
                                        .clip(MaterialTheme.shapes.medium)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(nome, color = Color.White)
                                Spacer(modifier = Modifier.height(4.dp))
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
                                Text(tipo, color = Color.Gray, fontSize = 12.sp)
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
            val icones = listOf("\u2302", "\uD83D\uDD0D", "+", "\uD83D\uDD14")
            icones.forEachIndexed { index, icon ->
                Text(
                    text = icon,
                    fontSize = 24.sp,
                    color = Color.White,
                    modifier = when (index) {
                        0 -> Modifier.clickable { onClickHome() } // casa
                        1 -> Modifier // lupa (já estamos na tela de pesquisa, então não faz nada)
                        else -> Modifier
                    }
                )
            }

            // Imagem do perfil
            Image(
                painter = painterResource(id = R.drawable.fotoperfil),
                contentDescription = "Foto do Perfil",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .border(width = 2.dp, color = Color.Gray, shape = CircleShape)
                    .clickable { onClickPerfil() }
            )

        }
    }
}