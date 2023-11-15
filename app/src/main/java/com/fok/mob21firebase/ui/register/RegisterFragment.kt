package com.fok.mob21firebase.ui.register

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil.isValidUrl
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.fok.mob21firebase.R
import com.fok.mob21firebase.databinding.FragmentRegisterBinding
import com.fok.mob21firebase.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {
    override val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupUIComponents() {
        super.setupUIComponents()

        binding.run {
            tvLogin.setOnClickListener {
                navController.popBackStack()
            }

            btnRegister.setOnClickListener {
                val email = etEmail.text.toString()
                val pass = etPassword.text.toString()
                val confirmPass = etPasswordAgain.text.toString()
                viewModel.register(email, pass, confirmPass)
            }
        }
    }

    override fun setupViewModelObserver() {
        super.setupViewModelObserver()

        lifecycleScope.launch {
            viewModel.success.collect {
                val action = RegisterFragmentDirections.toHome()
                navController.navigate(action)
            }
        }
    }
}