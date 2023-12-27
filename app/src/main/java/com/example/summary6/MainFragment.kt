package com.example.summary6

import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.example.summary6.adapters.KeyboardAdapter
import com.example.summary6.adapters.PincodeAdapter
import com.example.summary6.core.base.BaseFragment
import com.example.summary6.core.helper.Constants
import com.example.summary6.core.helper.Listeners
import com.example.summary6.core.helper.Observer
import com.example.summary6.core.keyboardItems
import com.example.summary6.databinding.FragmentMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate), Listeners, Observer {

    private val keyboardAdapter: KeyboardAdapter by lazy { KeyboardAdapter() }
    private val flow = MutableStateFlow("")

    override fun init() {
        setUpRecycler()
        listeners()
        observers()
    }

    override fun listeners() {
        keyboardAdapter.onNumberClick = {
            flow.value = flow.value + it.toString()
        }
        keyboardAdapter.onDeleteClick = {
            flow.value = flow.value.dropLast(1)
        }

        binding.etPasscode.addTextChangedListener {
            if (flow.value == Constants.PINCODE) {
                Snackbar.make(binding.root, "Success!", Snackbar.LENGTH_LONG).show()
                flow.value = ""
            } else {
                Snackbar.make(binding.root, "Incorrect PIN!", Snackbar.LENGTH_LONG).show()
            }
        }
    }




    private fun setUpRecycler() = with(binding.rvKeyboard) {
        adapter = keyboardAdapter
        keyboardAdapter.submitList(keyboardItems)
    }

    override fun observers() {
        lifecycleScope.launch {
            flow.collect {
                binding.etPasscode.setText(it)
            }
        }
    }


}