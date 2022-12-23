package com.example.focusestarttesttask.domain.entity

data class CardInfoEntity(
	val card: CardEntity?,
	val country: CountryEntity?,
	val bank: BankEntity?
)

data class CardEntity(
	val cardNumberLength: Int?,
	val cardNumberLuhn: Boolean?,
	val cardScheme: String?,
	val cardType: String?,
	val cardBrand: String?,
	val cardPrepareId: Boolean?
)

data class CountryEntity(
	val countryName: String?,
	val countryEmoji: String?,
	val countryLatitude: Int?,
	val countryLongitude: Int?
)

data class BankEntity(
	val bankName: String?,
	val bankUrl: String?,
	val bankPhone: String?,
	val bankCity: String?
)
