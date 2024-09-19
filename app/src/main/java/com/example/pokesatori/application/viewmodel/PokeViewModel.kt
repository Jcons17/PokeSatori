package com.example.pokesatori.application.viewmodel

import android.location.Location
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokesatori.domain.entity.Coordinates
import com.example.pokesatori.domain.entity.LoadedData
import com.example.pokesatori.domain.entity.Pokemon
import com.example.pokesatori.domain.entity.RemotePokemon
import com.example.pokesatori.repository.PokemonRepository
import com.example.pokesatori.services.LocationService
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
class PokeViewModel(
	private val pokemonRepository: PokemonRepository,
	private val locationService: LocationService,
) : ViewModel() {
	var randomPokemonState by mutableStateOf(LoadedData<Pokemon>(data = null))
		private set

	var locationState by mutableStateOf(Coordinates())
		private set

	var distance by mutableDoubleStateOf(0.0)
		private set

	private var locationFlow : Flow<Location> = locationService.startLocationUpdates()

	private var locationListener: Job? = null;

	fun getPokemon() {
		viewModelScope.launch {
			getRandomPokemon()
		}
	}

	fun stopLocation() {
		locationListener?.cancel()
		locationState = Coordinates(null, null)
		distance = 0.0
	}

	fun startLocationUpdates() {
		locationListener = viewModelScope.launch {
			locationFlow.onEach {
				if (locationState.lat == null || locationState.long == null) {
					locationState = Coordinates(it.latitude, it.longitude)
				}

				val location1 = Location("punto de referencia")
				location1.latitude = locationState.lat!!
				location1.longitude = locationState.long!!

				distance = location1.distanceTo(it).toDouble();

				if (distance > 10.0) {
					getPokemon()
					locationState = Coordinates(it.latitude, it.longitude)
				}

			}.collect()

		}
	}

	private suspend fun getRandomPokemon() {
		randomPokemonState = randomPokemonState.copy(loading = true)
		val result = pokemonRepository.getRandomPokemon()
		randomPokemonState = randomPokemonState.copy(
			loading = false, data = result.toDomain()
		)
	}

}

private fun RemotePokemon.toDomain(): Pokemon {
	return let {
		Pokemon(title = it.name,
			frontImage = it.sprites.frontDefault ?: "https://upload.wikimedia.org/wikipedia/commons/thumb/5/51/Pokebola-pokeball-png-0.png/640px-Pokebola-pokeball-png-0.png",
			backImage = it.sprites.backDefault ?: "https://upload.wikimedia.org/wikipedia/commons/thumb/5/51/Pokebola-pokeball-png-0.png/640px-Pokebola-pokeball-png-0.png",
			abilities = it.abilities
			)
	}
}


