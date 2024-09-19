package com.example.pokesatori.domain.entity

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class RemotePokemon (
	val abilities: List<Ability>,
	@SerialName("base_experience")
	val baseExperience: Long,
	val forms: List<Species>,
	val height: Long,
	val id: Long,
	val name: String,
	val order: Long,
	val species: Species,
	val sprites: Sprites,
	val weight: Long
)

@Serializable
data class Ability (
	val ability: Species,

	@SerialName("is_hidden")
	val isHidden: Boolean,

	val slot: Long
)

@Serializable
data class Species (
	val name: String,
	val url: String
)


@Serializable
data class Sprites (
	@SerialName("back_default")
	val backDefault: String?,

	@SerialName("back_shiny")
	val backShiny: String?,

	@SerialName("front_default")
	val frontDefault: String?,

	@SerialName("front_shiny")
	val frontShiny: String?,
)
