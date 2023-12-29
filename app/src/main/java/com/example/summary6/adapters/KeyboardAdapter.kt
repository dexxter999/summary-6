package com.example.summary6.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.summary6.core.helper.KeyboardType
import com.example.summary6.data.KeyboardItem
import com.example.summary6.databinding.ButtonDeleteBinding
import com.example.summary6.databinding.ButtonFingerprintBinding
import com.example.summary6.databinding.ButtonNumberBinding

class KeyboardAdapter : ListAdapter<KeyboardItem, RecyclerView.ViewHolder>(KeyboardItemCallback()) {

    var onNumberClick: ((number: Int) -> Unit)? = null
    var onDeleteClick: (() -> Unit)? = null
    var onFingerprintClick: (() -> Unit)? = null

    inner class NumberItemViewHolder(private val binding: ButtonNumberBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(keyboardItem: KeyboardItem) = with(binding) {
            buttonNumber.text = keyboardItem.number.toString()
            buttonNumber.setOnClickListener { onNumberClick?.invoke(keyboardItem.number!!) }
        }
    }

    inner class DeleteItemViewHolder(private val binding: ButtonDeleteBinding) :
        RecyclerView.ViewHolder(binding.buttonDelete) {
        fun bind() {
            binding.buttonDelete.setOnClickListener { onDeleteClick?.invoke() }
        }
    }

    inner class FingerPrintItemViewHolder(private val binding: ButtonFingerprintBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() = binding.buttonFingerprint.setOnClickListener { onFingerprintClick?.invoke() }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            KeyboardType.NUMBER.type -> NumberItemViewHolder(
                ButtonNumberBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            KeyboardType.FINGERPRINT.type -> FingerPrintItemViewHolder(
                ButtonFingerprintBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> DeleteItemViewHolder(
                ButtonDeleteBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NumberItemViewHolder -> holder.bind(getItem(position))
            is DeleteItemViewHolder -> holder.bind()
            is FingerPrintItemViewHolder -> holder.bind()
        }
    }

}