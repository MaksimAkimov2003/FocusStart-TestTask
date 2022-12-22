package com.example.focusestarttesttask.data.api

import com.example.focusestarttesttask.data.models.CardInfoModel
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://lookup.binlist.net/"

interface CardInfoService {

	@GET(value = "{cardBIN}")
	suspend fun getCardInformation(
		@Path(value = "cardBIN")
		cardBin: String
	): CardInfoModel
}