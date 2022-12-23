package com.example.focusestarttesttask.presentation

sealed class CardInfoState {

	object Initial : CardInfoState()

	object Loading : CardInfoState()

	data class Content(
		val cardNumberLength: Int?,
		val cardNumberLuhn: Boolean?,
		val cardScheme: String?,
		val cardType: String?,
		val cardBrand: String?,
		val cardPrepareId: Boolean?,

		val countryName: String?,
		val countryEmoji: String?,
		val countryLatitude: Int?,
		val countryLongitude: Int?,

		val bankName: String?,
		val bankUrl: String?,
		val bankPhone: String?,
		val bankCity: String?
	) : CardInfoState()

	object Error : CardInfoState()
}