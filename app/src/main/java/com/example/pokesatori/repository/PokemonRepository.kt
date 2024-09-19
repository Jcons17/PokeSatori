package com.example.pokesatori.repository

import com.example.pokesatori.domain.entity.RemotePokemon
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText


class PokemonRepository(private val client: HttpClient) {

	suspend fun getRandomPokemon(): RemotePokemon {
		val randomNumber = (1..898).random()
		val response = client.get(urlString = "https://pokeapi.co/api/v2/pokemon/$randomNumber");

		return response.body<RemotePokemon>();
	}
}
