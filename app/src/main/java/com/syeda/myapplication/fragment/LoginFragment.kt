package com.syeda.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.syeda.myapplication.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityLoginBinding.inflate(layoutInflater)

        binding.btnLogin.setOnClickListener {
            val action = LoginFragmentDirections.openActionToMainFragment(binding.etUsername.text.toString())
            findNavController().navigate(action)
        }
        return binding.root
    }
}
