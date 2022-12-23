package com.example.focusestarttesttask.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.focusestarttesttask.domain.useCases.GetCardInfoUseCase
import kotlinx.coroutines.launch

class CardInfoViewModel(
	private val getCardInfoUseCase: GetCardInfoUseCase
) : ViewModel() {

	fun getCardDetails(cardBin: String) {
		viewModelScope.launch {
			getCardInfoUseCase.execute(cardBin)
		}
	}

}