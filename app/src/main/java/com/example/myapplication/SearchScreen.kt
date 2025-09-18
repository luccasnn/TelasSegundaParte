package com.example.myapplication


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun TwitchSearchScreen(navController: NavController) {
    var searchText by remember { mutableStateOf("") }
    var isSearchBarFocused by remember { mutableStateOf(false) }
    val categorias = remember {
        listOf(
            Categoria("League of Legends", R.drawable.lol, "MOBA", "123k"),
            Categoria("CS2", R.drawable.cs2, "FPS", "98k"),
            Categoria("Dota 2", R.drawable.dota, "MOBA", "75k"),
            Categoria("Valorant", R.drawable.valorant, "FPS", "150k"),
            Categoria("GTA V", R.drawable.gta, "Aventura", "88k"),
            Categoria("IRL", R.drawable.irl, "Vida Real", "45k")
        )
    }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(1f)
                    .background(Color.DarkGray, shape = CircleShape)
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Icon(painter = painterResource(id = android.R.drawable.ic_menu_search), contentDescription = "Search Icon", tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Box(modifier = Modifier.weight(1f)) {
                    BasicTextField(
                        value = searchText,
                        onValueChange = { searchText = it },
                        textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
                        cursorBrush = SolidColor(Color.White),
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .onFocusChanged { focusState -> isSearchBarFocused = focusState.isFocused }
                    )
                    if (searchText.isEmpty()) {
                        Text("Procurar", color = Color.Gray, fontSize = 16.sp)
                    }
                }
                if (searchText.isNotEmpty()) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                        contentDescription = "Clear Search",
                        tint = Color.White,
                        modifier = Modifier.clickable { searchText = "" }
                    )
                }
            }
            if (isSearchBarFocused) {
                TextButton(onClick = { isSearchBarFocused = false; searchText = "" }) {
                    Text("Cancelar", color = Color.White)
                }
            }
        }

        if (isSearchBarFocused && searchText.isBlank()) {
            LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
                item { Text("PESQUISAS RECENTES", color = Color.Gray, fontWeight = FontWeight.Bold) }
                items(listOf("Valorant", "League of Legends")) { pesquisa ->
                    Text(pesquisa, color = Color.White, modifier = Modifier.padding(vertical = 8.dp))
                }
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                val filteredList = categorias.filter { it.nome.contains(searchText, ignoreCase = true) }
                items(filteredList) { categoria ->
                    Card(
                        modifier = Modifier.clickable { },
                        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = categoria.imagemRes),
                                contentDescription = "Capa de ${categoria.nome}",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.aspectRatio(3f / 5f)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(categoria.nome, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            Text(categoria.espectadores, color = Color.Gray, fontSize = 12.sp)
                        }
                    }
                }
            }
        }
    }
}
