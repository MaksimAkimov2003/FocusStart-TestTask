package com.example.focusestarttesttask.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.focusestarttesttask.databinding.RecyclerItemBinding
import com.example.focusestarttesttask.domain.entity.RequestEntity

class RecyclerViewHolder(
	private val binding: RecyclerItemBinding
) : RecyclerView.ViewHolder(binding.root) {

	companion object {

		fun from(parent: ViewGroup): RecyclerViewHolder {
			val inflater = LayoutInflater.from(parent.context)
			val binding = RecyclerItemBinding.inflate(inflater, parent, false)
			return RecyclerViewHolder(binding)
		}
	}

	fun bind(request: RequestEntity, listener: IRecyclerItemCallback) {
		with(binding) {
			requestText.text = request.request
			repeatRequestButton.setOnClickListener { listener.onRecyclerItemClick(request.request) }
		}
	}
}