package com.example.focusestarttesttask.domain.repository

interface CardsRepository {

	suspend fun getCardDetailsFromNetwork(cardBin: String)
}