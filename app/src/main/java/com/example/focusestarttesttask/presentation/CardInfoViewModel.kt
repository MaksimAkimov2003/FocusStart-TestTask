package com.example.focusestarttesttask.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.focusestarttesttask.domain.entity.CardInfoEntity
import com.example.focusestarttesttask.domain.useCases.GetCardInfoUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardInfoViewModel(
	private val getCardInfoUseCase: GetCardInfoUseCase
) : ViewModel() {

	private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
		onError("Exception handled: ${throwable.localizedMessage}")
	}

	private val _state = MutableLiveData<CardInfoState>(CardInfoState.Initial)
	val state: LiveData<CardInfoState> = _state

	fun getCardDetails(cardBin: String) {

		_state.value = CardInfoState.Loading
		viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
			try {
				val response = getCardInfoUseCase.execute(cardBin)
				setContentState(response)
			} catch (e: Error) {
				onError(e.message.toString())
			}
		}
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

	private fun onError(message: String) {
		_state.postValue(CardInfoState.Error)
		Log.e("ERRORYCH", message)
	}
}