package com.example.pokesatori.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.pokesatori.ui.theme.GreenPrincipal


@Composable
fun GetPokemonButton(onTap: () -> Unit = {} ) {
	Box(
		modifier = Modifier
			.fillMaxWidth()
			.padding(vertical = 24.dp)

	) {
		Text(
			"Mostrar Pokemon",
			modifier = Modifier
				.align(Alignment.Center)
				.clip(RoundedCornerShape(8.dp))
				.background(GreenPrincipal)
				.padding(8.dp)
				.clickable { onTap() }
		)
	}
}
