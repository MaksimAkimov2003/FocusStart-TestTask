package com.example.focusestarttesttask.data.storage

import android.content.Context
import com.example.focusestarttesttask.R
import com.example.focusestarttesttask.domain.entity.RequestEntity

class RequestsStorage(context: Context) {

	private companion object {

		const val SHARED_PREFS_REQUESTS = R.string.SHARED_PREFS_REQUESTS.toString()
		const val REQUESTS_SET_NAME = R.string.REQUESTS_SET_NAME.toString()
	}

	private val sharedPrefsRequests = context.getSharedPreferences(SHARED_PREFS_REQUESTS, Context.MODE_PRIVATE)

	fun saveSetOfUsersRequests(requestEntitySet: MutableSet<RequestEntity>) {
		val savingSet = mapRequestEntitySetToStringSet(requestEntitySet)

		sharedPrefsRequests.edit().clear().apply()
		sharedPrefsRequests.edit().putStringSet(REQUESTS_SET_NAME, savingSet).apply()
	}

	fun getSetOfUsersRequests(): MutableSet<RequestEntity>? {
		return mapStringSetToRequestEntitySet(set = sharedPrefsRequests.getStringSet(REQUESTS_SET_NAME, mutableSetOf()))
	}

	private fun mapStringSetToRequestEntitySet(set: MutableSet<String>?): MutableSet<RequestEntity>? {
		if (set == null) return null

		val newSet: MutableSet<RequestEntity> = mutableSetOf()

		for (item in set) {
			val requestEntity = RequestEntity(request = item)
			newSet.add(requestEntity)
		}

		return newSet
	}

	private fun mapRequestEntitySetToStringSet(set: MutableSet<RequestEntity>): MutableSet<String> {
		val newSet: MutableSet<String> = mutableSetOf()

		for (item in set) {
			newSet.add(item.request)
		}

		return newSet
	}

}