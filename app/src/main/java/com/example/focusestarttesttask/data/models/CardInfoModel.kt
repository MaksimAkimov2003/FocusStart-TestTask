package com.example.focusestarttesttask.data.models

import com.squareup.moshi.Json

data class CardInfoModel(
	@Json(name = "number") val cardNumber: CardNumber?,
	@Json(name = "scheme") val cardScheme: String?,
	@Json(name = "type") val cardType: String?,
	@Json(name = "brand") val cardBrand: String?,
	@Json(name = "prepaid") val cardPrepareId: Boolean?,
	@Json(name = "country") val country: Country?,
	@Json(name = "bank") val bank: Bank?
)

data class Bank(
	@Json(name = "name") val bankName: String?,
	@Json(name = "url") val bankUrl: String?,
	@Json(name = "phone") val bankPhone: String?,
	@Json(name = "city") val bankCity: String?
)

data class Country(
	@Json(name = "numeric") val countryNumeric: Int?,
	@Json(name = "alpha2") val countryAlpha2: String?,
	@Json(name = "name") val countryName: String?,
	@Json(name = "emoji") val countryEmoji: String?,
	@Json(name = "currency") val countryCurrency: String?,
	@Json(name = "latitude") val countryLatitude: Int?,
	@Json(name = "longitude") val countryLongitude: Int?
)

data class CardNumber(
	@Json(name = "length") val cardNumberLength: Int?,
	@Json(name = "luhn") val cardNumberLuhn: Boolean?
)

