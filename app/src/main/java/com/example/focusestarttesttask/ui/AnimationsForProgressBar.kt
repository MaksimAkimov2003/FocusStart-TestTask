package com.example.focusestarttesttask.ui

import android.view.View
import androidx.core.view.isVisible

private const val ALPHA_VISIBLE = 1F
private const val ALPHA_INVISIBLE = 0F
private const val ALPHA_DURATION = 100L

fun View.showWithFade() {
	animate().cancel()

	if (isVisible && alpha == ALPHA_VISIBLE) {
		return
	}
	if (alpha == ALPHA_VISIBLE) {
		alpha = ALPHA_INVISIBLE
	}

	animate()
		.alpha(ALPHA_VISIBLE)
		.setDuration(ALPHA_DURATION)
		.withStartAction { isVisible = true }
		.start()
}

fun View.hideWithFade() {
	animate().cancel()

	if (!isVisible) {
		return
	}

	animate()
		.alpha(ALPHA_INVISIBLE)
		.setDuration(ALPHA_DURATION)
		.withEndAction {
			isVisible = false
			alpha = ALPHA_VISIBLE
		}
		.start()
}