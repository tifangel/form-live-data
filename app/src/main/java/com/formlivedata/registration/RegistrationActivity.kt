package com.formlivedata.registration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.formlivedata.R
import com.formlivedata.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var viewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registration)
        binding.lifecycleOwner = this

        setSupportActionBar(binding.toolbar)

        viewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)
        binding.regis = viewModel
        viewModel.setupLiveData(this, this)
    }
}