package com.example.pokesatori.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pokesatori.application.viewmodel.PokeViewModel
import kotlin.text.append
import kotlin.text.forEach


@Composable
fun PokemonDetail(vm: PokeViewModel, modifier: Modifier = Modifier) {
	Box(modifier = modifier.fillMaxSize()){
		val data = vm.randomPokemonState

		if (data.loading) Box(modifier = Modifier.align(Alignment.Center)) {
			CircularProgressIndicator(modifier.align(alignment = Alignment.Center))
		}
		data.data?.let {
			val annotatedString = buildAnnotatedString {
				it.abilities.forEach { item ->
					withStyle(style = SpanStyle()) { append("• ") }
					append(item.ability.name)
					append("\n")
				}
			}

			Column(
				modifier = Modifier
					.fillMaxHeight()
					.align(Alignment.Center),
				horizontalAlignment = Alignment.CenterHorizontally,
				verticalArrangement = Arrangement.Center
			) {
				Row {
					AsyncImage(
						model = it.frontImage,
						contentDescription = "Imagen de frente ${it.title}",
						modifier = Modifier.size(128.dp)
					)
					AsyncImage(
						model = it.backImage,
						contentDescription = "Imagen por detras ${it.title}",
						modifier = Modifier.size(128.dp)
					)
				}
				Text(
					text = "Nombre: ${it.title}",
					fontSize = 18.sp
				)
				Text(text = "Habilidades")
				Text(text = annotatedString)

			}

		}

	}
}


