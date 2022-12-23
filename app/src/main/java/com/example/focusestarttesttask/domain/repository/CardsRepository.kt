package com.example.focusestarttesttask.domain.repository

import com.example.focusestarttesttask.domain.entity.CardInfoEntity

interface CardsRepository {

	suspend fun getCardDetailsFromNetwork(cardBin: String): CardInfoEntity
}