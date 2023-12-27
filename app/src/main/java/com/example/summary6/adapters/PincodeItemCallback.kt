package com.example.summary6.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.summary6.data.PincodeItem

class PincodeItemCallback : DiffUtil.ItemCallback<PincodeItem>() {
    override fun areItemsTheSame(oldItem: PincodeItem, newItem: PincodeItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PincodeItem, newItem: PincodeItem): Boolean {
        return oldItem == newItem
    }
}