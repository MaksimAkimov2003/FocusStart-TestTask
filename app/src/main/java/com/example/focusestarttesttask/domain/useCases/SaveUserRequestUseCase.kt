package com.example.focusestarttesttask.domain.useCases

import com.example.focusestarttesttask.domain.entity.RequestEntity
import com.example.focusestarttesttask.domain.repository.CardsRepository

class SaveUserRequestUseCase(private val cardsRepository: CardsRepository) {

	fun execute(requestEntitySet: MutableSet<RequestEntity>) {
		cardsRepository.saveUsersRequests(requestEntitySet)
	}
}