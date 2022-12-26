package com.example.focusestarttesttask.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.focusestarttesttask.R
import com.example.focusestarttesttask.databinding.ActivityCardInfoBinding
import com.example.focusestarttesttask.databinding.BottomSheetBinding
import com.example.focusestarttesttask.presentation.CardInfoState
import com.example.focusestarttesttask.presentation.CardInfoViewModel
import com.example.focusestarttesttask.ui.recycler.IRecyclerItemCallback
import com.example.focusestarttesttask.ui.recycler.RecyclerAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardInfoActivity : AppCompatActivity(), TextView.OnEditorActionListener, IRecyclerItemCallback {

	private val binding by lazy { ActivityCardInfoBinding.inflate(layoutInflater) }
	private val bottomSheetBinding by lazy { BottomSheetBinding.inflate(layoutInflater) }
	private val adapter = RecyclerAdapter(this)
	private val viewModel by viewModel<CardInfoViewModel>()

	private lateinit var bottomSheetDialog: BottomSheetDialog

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		binding.progressBar.hideWithFade()
		createBottomSheet()
		setObserves()
		setListeners()
	}

	override fun onEditorAction(p0: TextView?, actionId: Int, p2: KeyEvent?): Boolean {
		if (actionId == EditorInfo.IME_ACTION_SEARCH) {
			hideKeyboard()
			checkUserRequestForNull()
			return true
		}
		return false
	}

	override fun onRecyclerItemClick(request: String) {
		viewModel.getCardDetails(request)
		binding.searchCardInfo.setText(request)
		bottomSheetDialog.dismiss()
	}

	private fun checkUserRequestForNull() {
		if (binding.searchCardInfo.text.toString() != "") {
			viewModel.getCardDetails(binding.searchCardInfo.text.toString())
			return
		}
		showErrors()
	}

	private fun createBottomSheet() {
		bottomSheetDialog = BottomSheetDialog(this)
		bottomSheetDialog.setContentView(bottomSheetBinding.root)
		bottomSheetBinding.recyclerView.adapter = adapter
	}

	private fun setListeners() {
		binding.searchCardInfo.setOnEditorActionListener(this)
		binding.searchCardInfoLayout.setEndIconOnClickListener { clearEditText() }
		binding.showHistoryButton.setOnClickListener { showBottomSheet() }
	}

	private fun showBottomSheet() {
		bottomSheetDialog.show()
	}

	private fun setObserves() {
		viewModel.state.observe(this, ::handleState)
		viewModel.requests.observe(this) {
			adapter.submitList(it.toList())
		}
	}

	private fun handleState(state: CardInfoState) {
		when (state) {
			is CardInfoState.Initial -> Unit

			is CardInfoState.Loading -> renderLoadingState()

			is CardInfoState.Content -> renderContentState(state)

			is CardInfoState.Error   -> showErrors()
		}
	}

	private fun renderLoadingState() {
		binding.progressBar.showWithFade()
	}

	private fun renderContentState(state: CardInfoState.Content) {

		with(binding) {
			informationLayout.visibility = View.VISIBLE
			binding.progressBar.hideWithFade()
			schemeNetworkValue.text = state.cardScheme
			bankTelephone.text = convertTelephoneNumber(state.bankPhone)
			bankSite.text = state.bankUrl
			bankName.text = state.bankName
			countryValue.text = buildString {
				append(state.countryEmoji)
				append(" ")
				append(state.countryName)
			}
			prepaidValue.text = state.cardPrepareId.toString()
			typeValue.text = state.cardType
			luhnValue.text = state.cardNumberLuhn.toString()
			lengthValue.text = state.cardNumberLength.toString()
			brandValue.text = state.cardBrand
		}

		if (state.countryLatitude != null && state.countryLongitude != null) {
			binding.countryCoordinates.setOnClickListener {
				intentToMapsApplication(
					latitude = state.countryLatitude,
					longitude = state.countryLongitude
				)
			}
		}

		if (state.bankPhone != null) {
			binding.bankTelephone.setOnClickListener { intentToCallApp(binding.bankTelephone.text.toString()) }
		}
	}

	private fun intentToCallApp(number: String?) {
		val intent = Intent()

		intent.action = Intent.ACTION_VIEW
		intent.data = Uri.parse("tel:$number")

		startActivity(intent)
	}

	private fun convertTelephoneNumber(number: String?): String? {
		if (number == null) return null

		var newNumber = PhoneNumberUtils.normalizeNumber(number)

		if (newNumber.length > 12) {
			newNumber = newNumber.substring(0, 11)
		}

		if (newNumber[0] != '+') {
			newNumber = "+$newNumber"
		}

		return newNumber
	}

	private fun intentToMapsApplication(latitude: Int, longitude: Int) {
		val intent = Intent()

		intent.action = Intent.ACTION_VIEW
		intent.data = Uri.parse("geo:$latitude, $longitude")

		startActivity(intent)
	}

	private fun hideKeyboard() {
		this.currentFocus?.let { view ->
			val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
			imm?.hideSoftInputFromWindow(view.windowToken, 0)
		}
	}

	private fun showErrors() {
		binding.progressBar.hideWithFade()

		with(binding.searchCardInfoLayout) {
			error = getString(R.string.error_message)
			errorIconDrawable = null
		}

		binding.searchCardInfo.doOnTextChanged { _, _, _, _ -> hideErrors() }
	}

	private fun hideErrors() {
		binding.searchCardInfoLayout.error = null
	}

	private fun clearEditText() {
		binding.searchCardInfo.text = null
	}
}