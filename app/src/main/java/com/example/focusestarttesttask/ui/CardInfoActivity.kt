package com.example.focusestarttesttask.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.focusestarttesttask.R
import com.example.focusestarttesttask.databinding.ActivityCardInfoBinding
import com.example.focusestarttesttask.presentation.CardInfoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardInfoActivity : AppCompatActivity(), TextView.OnEditorActionListener {

	private lateinit var binding: ActivityCardInfoBinding
	private val viewModel by viewModel<CardInfoViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityCardInfoBinding.inflate(layoutInflater)
		setContentView(binding.root)
		setListeners()
	}

	override fun onEditorAction(p0: TextView?, actionId: Int, p2: KeyEvent?): Boolean {
		if (actionId == EditorInfo.IME_ACTION_SEARCH) {
			hideKeyboard()
			viewModel.getCardDetails(binding.searchCardInfo.text.toString())

			return true;
		}
		return false
	}

	private fun setListeners() {
		binding.searchCardInfo.setOnEditorActionListener(this)
		binding.searchCardInfoLayout.setEndIconOnClickListener { clearEditText() }
		binding.countryCoordinates.setOnClickListener { intentToMapsApplication() }
	}

	private fun hideKeyboard() {
		this.currentFocus?.let { view ->
			val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
			imm?.hideSoftInputFromWindow(view.windowToken, 0)
		}
	}

	private fun showErrors() {
		binding.searchCardInfoLayout.error = getString(R.string.error_message)
		binding.searchCardInfoLayout.errorIconDrawable = null
	}

	private fun clearEditText() {
		binding.searchCardInfo.text = null
	}

	private fun intentToMapsApplication() {
		val intent = Intent()

		intent.action = Intent.ACTION_VIEW
		intent.data = Uri.parse("geo:" + 56.toString() + ", " + 10.toString())

		startActivity(intent)
	}
}