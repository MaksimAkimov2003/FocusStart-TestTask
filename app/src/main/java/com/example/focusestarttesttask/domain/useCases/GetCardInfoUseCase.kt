package com.example.focusestarttesttask.domain.useCases

import com.example.focusestarttesttask.domain.repository.CardsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCardInfoUseCase(private val cardsRepository: CardsRepository) {

	suspend fun execute(cardBin: String) {
		withContext(Dispatchers.IO) {
			cardsRepository.getCardDetailsFromNetwork(cardBin)
		}
	}
}