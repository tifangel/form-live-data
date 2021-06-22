package com.formlivedata.login

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.formlivedata.utils.validateEmail
import com.formlivedata.utils.validatePassword

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    var email: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()
    var isValid: MutableLiveData<Boolean> = MutableLiveData()

    var errorEmail: MutableLiveData<String> = MutableLiveData()
    var errorPassword: MutableLiveData<String> = MutableLiveData()

    private var isEmailValid: Boolean = false
    private var isPasswordValid: Boolean = false

    fun setupLiveData(lifecycleOwner: LifecycleOwner, context: Context) {
        email.observe(lifecycleOwner, { email ->
            val validationModel = email.validateEmail(context)
            isEmailValid = validationModel.isValid
            validateInput(isEmailValid, isPasswordValid)
            errorEmail.postValue(validationModel.message)
        })
        password.observe(lifecycleOwner, { password ->
            val validationModel = password.validatePassword(context)
            isPasswordValid = validationModel.isValid
            validateInput(isEmailValid, isPasswordValid)
            errorPassword.postValue(validationModel.message)
        })
    }

    private fun validateInput(email: Boolean, password: Boolean) {
        isValid.postValue(email && password)
    }

    fun clickLogin(view: View) {
        Toast.makeText(view.context, "${email.value} ${password.value}", Toast.LENGTH_SHORT).show()
    }

}