package com.example.focusestarttesttask.ui.recycler

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.focusestarttesttask.databinding.TestItemBinding
import com.example.focusestarttesttask.domain.entity.RequestEntity

class RecyclerViewHolder(
	private val binding: TestItemBinding
) : RecyclerView.ViewHolder(binding.root) {

	companion object {

		fun from(parent: ViewGroup): RecyclerViewHolder {
			val inflater = LayoutInflater.from(parent.context)
			val binding = TestItemBinding.inflate(inflater, parent, false)
			return RecyclerViewHolder(binding)
		}
	}

	fun bind(request: RequestEntity, listener: IRecyclerItemCallback) {
		with(binding) {
			mainTextView.text = request.request
//			binding.repeatRequestButton.setOnClickListener { listener.onRecyclerItemClick(request.request) }
		}
		Log.e("BBB", "RecyclerViewHolder")
	}
}