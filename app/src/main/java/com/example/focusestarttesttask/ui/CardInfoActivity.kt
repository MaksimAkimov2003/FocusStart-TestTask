package com.example.focusestarttesttask.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.focusestarttesttask.databinding.ActivityCardInfoBinding
import com.example.focusestarttesttask.presentation.CardInfoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardInfoActivity : AppCompatActivity() {

	private lateinit var binding: ActivityCardInfoBinding
	private val viewModel by viewModel<CardInfoViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityCardInfoBinding.inflate(layoutInflater)
		setContentView(binding.root)

		viewModel.getCardDetails(45717360)

	}
}