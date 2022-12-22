package com.example.focusestarttesttask.data.repository

import com.example.focusestarttesttask.data.api.CardInfoService
import com.example.focusestarttesttask.domain.repository.CardsRepository

class CardsRepositoryImpl(private val cardInfoService: CardInfoService) : CardsRepository {

	override suspend fun getCardDetailsFromNetwork(cardBin: Int) {
		cardInfoService.getCardInformation(cardBin.toString())
	}
}