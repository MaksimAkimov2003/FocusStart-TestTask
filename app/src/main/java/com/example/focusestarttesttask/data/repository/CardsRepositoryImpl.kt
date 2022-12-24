package com.example.focusestarttesttask.data.repository

import com.example.focusestarttesttask.data.api.CardInfoService
import com.example.focusestarttesttask.data.models.CardInfoModel
import com.example.focusestarttesttask.data.storage.RequestsStorage
import com.example.focusestarttesttask.domain.entity.BankEntity
import com.example.focusestarttesttask.domain.entity.CardEntity
import com.example.focusestarttesttask.domain.entity.CardInfoEntity
import com.example.focusestarttesttask.domain.entity.CountryEntity
import com.example.focusestarttesttask.domain.repository.CardsRepository

class CardsRepositoryImpl(
	private val cardInfoService: CardInfoService,
	private val requestsStorage: RequestsStorage
) : CardsRepository {

	override suspend fun getCardDetailsFromNetwork(cardBin: String): CardInfoEntity {
		val cardInfoModel = cardInfoService.getCardInformation(cardBin)

		return mapModelToEntity(cardInfoModel)
	}

	private fun mapModelToEntity(cardInfoModel: CardInfoModel): CardInfoEntity {
		val bank =
			BankEntity(
				bankName = cardInfoModel.bank?.bankName,
				bankUrl = cardInfoModel.bank?.bankUrl,
				bankPhone = cardInfoModel.bank?.bankPhone,
				bankCity = cardInfoModel.bank?.bankCity
			)

		val country = CountryEntity(
			countryName = cardInfoModel.country?.countryName,
			countryEmoji = cardInfoModel.country?.countryEmoji,
			countryLatitude = cardInfoModel.country?.countryLatitude,
			countryLongitude = cardInfoModel.country?.countryLongitude
		)

		val card = CardEntity(
			cardNumberLength = cardInfoModel.cardNumber?.cardNumberLength,
			cardNumberLuhn = cardInfoModel.cardNumber?.cardNumberLuhn,
			cardScheme = cardInfoModel.cardScheme,
			cardType = cardInfoModel.cardType,
			cardBrand = cardInfoModel.cardBrand,
			cardPrepareId = cardInfoModel.cardPrepareId
		)

		return CardInfoEntity(card = card, country = country, bank = bank)
	}
}