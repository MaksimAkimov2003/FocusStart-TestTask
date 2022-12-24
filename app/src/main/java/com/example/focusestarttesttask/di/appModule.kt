package com.example.focusestarttesttask.di

import com.example.focusestarttesttask.data.api.provideCardInfoService
import com.example.focusestarttesttask.data.api.provideLoggingInterceptor
import com.example.focusestarttesttask.data.api.provideMoshi
import com.example.focusestarttesttask.data.api.provideOkHttpClient
import com.example.focusestarttesttask.data.repository.CardsRepositoryImpl
import com.example.focusestarttesttask.data.storage.RequestsStorage
import com.example.focusestarttesttask.domain.repository.CardsRepository
import com.example.focusestarttesttask.domain.useCases.GetCardInfoUseCase
import com.example.focusestarttesttask.presentation.CardInfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
	single { provideLoggingInterceptor() }
	single { provideOkHttpClient(loggingInterceptor = get()) }
	single { provideMoshi() }
	single { provideCardInfoService(okHttpClient = get(), moshi = get()) }
	single { RequestsStorage(context = get()) }
	single<CardsRepository> {
		CardsRepositoryImpl(cardInfoService = get(), requestsStorage = get())
	}
	factory {
		GetCardInfoUseCase(cardsRepository = get())
	}
	viewModel {
		CardInfoViewModel(getCardInfoUseCase = get())
	}

}