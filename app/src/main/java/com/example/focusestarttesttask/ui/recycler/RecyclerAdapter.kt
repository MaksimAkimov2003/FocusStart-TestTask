package com.example.focusestarttesttask.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.focusestarttesttask.domain.entity.RequestEntity
import com.example.focusestarttesttask.ui.recycler.IRecyclerItemCallback
import com.example.focusestarttesttask.ui.recycler.RecyclerViewHolder

class RecyclerAdapter(private val listener: IRecyclerItemCallback) :
	ListAdapter<RequestEntity, RecyclerViewHolder>(DiffCallback) {

	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): RecyclerViewHolder =
		RecyclerViewHolder.from(parent)

	override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
		holder.bind(request = getItem(position), listener = listener)
	}
}

object DiffCallback : DiffUtil.ItemCallback<RequestEntity>() {

	override fun areItemsTheSame(
		oldItem: RequestEntity,
		newItem: RequestEntity
	): Boolean = oldItem == newItem

	override fun areContentsTheSame(
		oldItem: RequestEntity,
		newItem: RequestEntity
	): Boolean = oldItem.request == newItem.request

}