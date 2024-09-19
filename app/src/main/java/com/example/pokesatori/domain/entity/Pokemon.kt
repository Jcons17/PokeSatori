package com.example.pokesatori.domain.entity

data class Pokemon(
	val title: String, val frontImage: String, val backImage: String,
	val abilities: List<Ability> = emptyList(),
) {}
