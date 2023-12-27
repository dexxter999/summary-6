package com.example.summary6.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.summary6.data.PincodeItem
import com.example.summary6.databinding.PincodeEmptyItemBinding
import com.example.summary6.databinding.PincodeFilledItemBinding

class PincodeAdapter : ListAdapter<PincodeItem, RecyclerView.ViewHolder>(PincodeItemCallback()) {

    inner class EmptyPincodeViewHolder(private val binding: PincodeEmptyItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class FilledPincodeViewHolder(private val binding: PincodeFilledItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> EmptyPincodeViewHolder(
                PincodeEmptyItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> FilledPincodeViewHolder(
                PincodeFilledItemBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is EmptyPincodeViewHolder -> holder
            is FilledPincodeViewHolder -> holder
        }
    }

}