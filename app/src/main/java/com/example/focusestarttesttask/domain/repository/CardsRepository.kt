package com.example.focusestarttesttask.domain.repository

import com.example.focusestarttesttask.domain.entity.CardInfoEntity
import com.example.focusestarttesttask.domain.entity.RequestEntity

interface CardsRepository {

	suspend fun getCardDetailsFromNetwork(cardBin: String): CardInfoEntity

	fun saveUsersRequests(requestEntitySet: MutableSet<RequestEntity>)

	fun getUserRequests(): MutableSet<RequestEntity>?
}