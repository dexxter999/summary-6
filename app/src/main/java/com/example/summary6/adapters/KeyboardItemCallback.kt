package com.example.summary6.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.summary6.data.KeyboardItem

class KeyboardItemCallback : DiffUtil.ItemCallback<KeyboardItem>() {
    override fun areItemsTheSame(oldItem: KeyboardItem, newItem: KeyboardItem) = oldItem == newItem

    override fun areContentsTheSame(oldItem: KeyboardItem, newItem: KeyboardItem) =
        oldItem == newItem

}