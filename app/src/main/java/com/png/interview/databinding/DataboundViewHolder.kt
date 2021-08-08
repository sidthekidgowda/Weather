package com.png.interview.databinding

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class DataboundViewHolder<T : ViewDataBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root)

inline fun <reified T : ViewDataBinding, reified DATA : Any> DataboundViewHolder<ViewDataBinding>.onTypeWithData(
    data: List<Any>,
    position: Int,
    bindingBlock: (T, DATA) -> Unit
) {
    val binding = this.binding
    val item = data[position]

    if (binding is T && item is DATA) {
        bindingBlock(binding, item)
    }
}

inline fun <reified T : ViewDataBinding> T.toViewHolder() = DataboundViewHolder(this)