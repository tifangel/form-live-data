package com.formlivedata.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.formlivedata.R
import com.formlivedata.databinding.ActivityLoginBinding
import com.formlivedata.registration.RegistrationViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this

        setSupportActionBar(binding.toolbar)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.login = viewModel
        viewModel.setupLiveData(this, this)
    }
}