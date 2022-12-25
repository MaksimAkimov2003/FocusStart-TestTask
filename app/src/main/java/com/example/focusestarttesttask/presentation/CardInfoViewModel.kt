package com.example.focusestarttesttask.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.focusestarttesttask.domain.entity.CardInfoEntity
import com.example.focusestarttesttask.domain.entity.RequestEntity
import com.example.focusestarttesttask.domain.useCases.GetCardInfoUseCase
import com.example.focusestarttesttask.domain.useCases.GetSetOfUsersRequestsUseCase
import com.example.focusestarttesttask.domain.useCases.SaveUserRequestUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardInfoViewModel(
	private val getCardInfoUseCase: GetCardInfoUseCase,
	private val saveUserRequestUseCase: SaveUserRequestUseCase,
	private val getSetOfUsersRequestsUseCase: GetSetOfUsersRequestsUseCase
) : ViewModel() {

	private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
		onError()
	}

	private val _state = MutableLiveData<CardInfoState>(CardInfoState.Initial)
	private var _requests: MutableSet<RequestEntity> = mutableSetOf()

	val state: LiveData<CardInfoState> = _state
	val requests = MutableLiveData<MutableSet<RequestEntity>>()

	init {
		setRequestsList()
	}

	fun getCardDetails(cardBin: String) {
		_state.value = CardInfoState.Loading

		viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
			try {
				val response = getCardInfoUseCase.execute(cardBin)
				setContentState(response)
			} catch (e: Error) {
				onError()
			}
		}

		saveRequest(cardBin)
	}

	private fun setRequestsList() {
		if (getSetOfUsersRequestsUseCase.execute() != null) {
			_requests = getSetOfUsersRequestsUseCase.execute()!!
			requests.value = _requests
		}
	}

	private fun saveRequest(cardBin: String) {
		saveRequestInLiveData(cardBin)
		saveRequestInStorage()
	}

	private fun saveRequestInLiveData(cardBin: String) {
		val requestEntity = RequestEntity(request = cardBin)

		_requests.add(requestEntity)
		requests.value = _requests
	}

	private fun saveRequestInStorage() {
		saveUserRequestUseCase.execute(_requests)
	}

	private fun setContentState(response: CardInfoEntity) {
		_state.postValue(
			CardInfoState.Content(
				cardNumberLength = response.card?.cardNumberLength,
				cardNumberLuhn = response.card?.cardNumberLuhn,
				cardScheme = response.card?.cardScheme,
				cardType = response.card?.cardType,
				cardBrand = response.card?.cardBrand,
				cardPrepareId = response.card?.cardPrepareId,
				countryLongitude = response.country?.countryLongitude,
				countryLatitude = response.country?.countryLatitude,
				countryName = response.country?.countryName,
				countryEmoji = response.country?.countryEmoji,
				bankUrl = response.bank?.bankUrl,
				bankCity = response.bank?.bankCity,
				bankName = response.bank?.bankName,
				bankPhone = response.bank?.bankPhone
			)
		)
	}

	private fun onError() {
		_state.postValue(CardInfoState.Error)
	}
}