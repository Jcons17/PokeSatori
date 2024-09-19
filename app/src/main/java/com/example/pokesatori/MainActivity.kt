package com.example.pokesatori

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokesatori.repository.PokemonRepository
import com.example.pokesatori.application.viewmodel.PokeViewModel
import com.example.pokesatori.services.LocationService
import com.example.pokesatori.ui.screens.HomeScreen
import com.example.pokesatori.ui.theme.PokeSatoriTheme
import com.google.android.gms.location.LocationServices
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		val client = HttpClient() {
			install(ContentNegotiation) {
				json(Json {
					ignoreUnknownKeys = true
				})
			}
		}
		val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
		val locationService = LocationService(fusedLocationClient)

		setContent {
			PokeSatoriTheme {
				HomeScreen(vm = viewModel {
					PokeViewModel(
						PokemonRepository(client),
						locationService,
					)
				})
			}
		}
	}
}

