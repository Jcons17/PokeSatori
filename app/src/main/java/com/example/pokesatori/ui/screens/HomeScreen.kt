package com.example.pokesatori.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pokesatori.application.viewmodel.PokeViewModel
import com.example.pokesatori.ui.components.GetPokemonButton
import com.example.pokesatori.ui.components.PokemonDetail
import com.example.pokesatori.ui.components.SearchPokemonButton
import com.example.pokesatori.ui.theme.GreenTertiary

@Composable
fun HomeScreen(vm: PokeViewModel) {
	Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
		Box(modifier = Modifier.padding(innerPadding)) {
			Column(modifier = Modifier.fillMaxSize()) {
				GetPokemonButton(onTap = { vm.getPokemon() })
				PokemonDetail(vm, modifier = Modifier.weight(1F))
				Box() {
					Column(
						modifier = Modifier

							.fillMaxWidth()
					) {
						Box(
							modifier = Modifier
                                .padding(start = 16.dp)
                                .fillMaxWidth(fraction = vm.distance.toFloat() / 10)
                                .height(2.dp)
                                .background(color = GreenTertiary)
						) {
							AsyncImage(
									model = "https://i.gifer.com/ZJFD.gif",
								contentDescription = "walk",
								modifier = Modifier.height(16.dp)
							)						}
						Box(
							modifier = Modifier
                                .padding(start = 16.dp)
                                .fillMaxWidth(fraction = vm.distance.toFloat() / 10)
                                .height(2.dp)
                                .background(color = GreenTertiary)
						)
					}
				}
				SearchPokemonButton(modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
					searching = vm.locationState.lat != null,
					onPermissionGranted = {
						println("called")
						vm.locationState.lat?.let { vm.stopLocation() } ?: vm.startLocationUpdates()
					})
			}

		}
	}
}
