package com.example.summary6.presentation

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.summary6.adapters.KeyboardAdapter
import com.example.summary6.core.base.BaseFragment
import com.example.summary6.core.helper.Constants
import com.example.summary6.core.helper.Listeners
import com.example.summary6.core.helper.Observer
import com.example.summary6.core.helper.keyboardItems
import com.example.summary6.databinding.FragmentMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate), Listeners,
    Observer {

    private val keyboardAdapter: KeyboardAdapter by lazy { KeyboardAdapter() }
    private val viewModel: MainViewModel by viewModels()
    override fun init() {
        setUpRecycler()
        listeners()
        observers()
    }

    override fun listeners() {
        keyboardAdapter.onNumberClick = {
            viewModel.onEvent(MainEvent.NumberClick(it))
        }
        keyboardAdapter.onDeleteClick = {
            viewModel.onEvent(MainEvent.DeleteClick)
        }

        keyboardAdapter.onFingerprintClick = {
            Snackbar.make(binding.root, Constants.FINGERPRINT_MESSAGE, Snackbar.LENGTH_LONG).show()
        }

        binding.pinCode.setPasscodeEntryListener {
            if (viewModel.pinState.value == Constants.PIN_CODE) {
                Snackbar.make(binding.root, Constants.SUCCESS, Snackbar.LENGTH_LONG).show()
                viewModel.pinState.value = ""
            } else {
                Snackbar.make(binding.root, Constants.INCORRECT, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun setUpRecycler() = with(binding.rvKeyboard) {
        adapter = keyboardAdapter
        keyboardAdapter.submitList(keyboardItems)
    }

    override fun observers() {
        lifecycleScope.launch {
            viewModel.pinState.collect {
                binding.pinCode.setText(it)
            }
        }
    }
}