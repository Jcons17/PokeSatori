package com.example.pokesatori.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.example.pokesatori.ui.theme.GreenPrincipal
import com.example.pokesatori.ui.theme.GreenSecondary
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SearchPokemonButton(
	modifier: Modifier = Modifier, onPermissionGranted: () -> Unit = {}, searching: Boolean
) {
	val permissionState = rememberMultiplePermissionsState(
		permissions = listOf(
			android.Manifest.permission.ACCESS_FINE_LOCATION,
			android.Manifest.permission.ACCESS_COARSE_LOCATION
		)
	)

	val infiniteTransition = rememberInfiniteTransition(label = "")

	val scale by infiniteTransition.animateFloat(
		initialValue = 1.0f,
		targetValue = 1.2f,
		animationSpec = infiniteRepeatable(
			animation = tween(1000, easing = LinearEasing),
			repeatMode = RepeatMode.Reverse
		), label = ""
	)

	val currentScale = if (searching) scale else 1.0f

	Box(
		modifier = modifier
			.width(150.dp)
			.aspectRatio(1.0F)
			.scale(currentScale)
			.clip(CircleShape)
			.background(if (searching) GreenSecondary else GreenPrincipal)
			.clickable(onClick = {
				if (permissionState.allPermissionsGranted) {
					onPermissionGranted()
				} else {
					permissionState.launchMultiplePermissionRequest()
				}
			})

	) {
		Text("Buscar Pokemon", modifier = Modifier.align(Alignment.Center))
	}


}
