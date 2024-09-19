package com.example.pokesatori.domain.entity

data class LoadedData<T>(
	val data: T?,
	val loading: Boolean = false,
	val error: Boolean = false,
) {

}
