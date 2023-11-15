package com.fok.mob21firebase.ui.login

import android.app.Activity.RESULT_OK
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.fok.mob21firebase.R
import com.fok.mob21firebase.databinding.FragmentLoginBinding
import com.fok.mob21firebase.ui.base.BaseFragment
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override val viewModel: LoginViewModel by viewModels()
    private lateinit var signInClient: GoogleSignInClient

    private val googleSignInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        Log.d("debugging", "Result Code: ${result.resultCode}")
        if(result.resultCode == RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val acc = task.result!!
                Log.d("debugging", acc.displayName.toString())
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("debugging", e.message.toString())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupUIComponents() {
        super.setupUIComponents()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        signInClient = GoogleSignIn.getClient(requireActivity(), gso)

        binding.run {
            tvRegister.setOnClickListener {
                val action = LoginFragmentDirections.actionLoginToRegister()
                navController.navigate(action)
            }

            btnLogin.setOnClickListener {
                val email = etEmail.text.toString()
                val pass = etPassword.text.toString()
                viewModel.login(email, pass)
            }

            btnSignInWithGoogle.setOnClickListener {
                googleSignInLauncher.launch(signInClient.signInIntent)
            }
        }
    }

    override fun setupViewModelObserver() {
        super.setupViewModelObserver()

        lifecycleScope.launch {
            viewModel.success.collect {
                val action = LoginFragmentDirections.toHome()
                navController.navigate(action)
            }
        }

        lifecycleScope.launch {
            viewModel.greet.collect {
                binding.tvGreet.text = it
            }
        }
    }
}