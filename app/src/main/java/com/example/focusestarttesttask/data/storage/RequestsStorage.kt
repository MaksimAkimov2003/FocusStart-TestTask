package com.example.focusestarttesttask.data.storage

import android.content.Context
import com.example.focusestarttesttask.R
import com.example.focusestarttesttask.domain.entity.RequestEntity

class RequestsStorage(context: Context) {

	private companion object {

		const val SHARED_PREFS_REQUESTS = R.string.SHARED_PREFS_REQUESTS.toString()
	}

	private val sharedPrefsRequests = context.getSharedPreferences(SHARED_PREFS_REQUESTS, Context.MODE_PRIVATE)

	fun saveSetOfUsersRequests() {

		TODO("Прописать storage")
	}

	fun getSetOfUsersRequests(): Set<RequestEntity> {
		val request = sharedPrefsRequests.getStringSet("ss", setOf())

		val requestsSet = setOf<RequestEntity>()

		return requestsSet
	}

}