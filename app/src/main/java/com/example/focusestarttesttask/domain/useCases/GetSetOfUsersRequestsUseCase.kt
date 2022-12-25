package com.example.focusestarttesttask.domain.useCases

import com.example.focusestarttesttask.domain.entity.RequestEntity
import com.example.focusestarttesttask.domain.repository.CardsRepository

class GetSetOfUsersRequestsUseCase(private val cardsRepository: CardsRepository) {

	fun execute(): MutableSet<RequestEntity>? {
		return cardsRepository.getUserRequests()
	}
}