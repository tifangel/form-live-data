package com.formlivedata.registration

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.formlivedata.utils.validateEmail
import com.formlivedata.utils.validatePassword
import com.formlivedata.utils.validatePasswordConfirm

class RegistrationViewModel(application: Application) : AndroidViewModel(application) {

    var email: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()
    var passwordConfirm: MutableLiveData<String> = MutableLiveData()
    var isValid: MutableLiveData<Boolean> = MutableLiveData()

    var errorEmail: MutableLiveData<String> = MutableLiveData()
    var errorPassword: MutableLiveData<String> = MutableLiveData()
    var errorPassConfirm: MutableLiveData<String> = MutableLiveData()

    private var isEmailValid: Boolean = false
    private var isPasswordValid: Boolean = false
    private var isPassConfirmValid: Boolean = false

    fun setupLiveData(lifecycleOwner: LifecycleOwner, context: Context) {
        email.observe(lifecycleOwner, { email ->
            val validationModel = email.validateEmail(context)
            isEmailValid = validationModel.isValid
            validateInput(isEmailValid, isPasswordValid, isPassConfirmValid)
            errorEmail.postValue(validationModel.message)
        })
        password.observe(lifecycleOwner, { password ->
            val validationModel = password.validatePassword(context)
            isPasswordValid = validationModel.isValid
            validateInput(isEmailValid, isPasswordValid, isPassConfirmValid)
            errorPassword.postValue(validationModel.message)
        })
        passwordConfirm.observe(lifecycleOwner, { passconfirm ->
            val validationModel =
                password.value?.let { passconfirm.validatePasswordConfirm(context, it) }
            if (validationModel != null) {
                isPassConfirmValid = validationModel.isValid
                validateInput(isEmailValid, isPasswordValid, isPassConfirmValid)
                errorPassConfirm.postValue(validationModel.message)
            }

        })
    }

    private fun validateInput(email: Boolean, password: Boolean, passconfirm: Boolean) {
        isValid.postValue(email && password && passconfirm)
    }

    fun clickLogin(view: View) {
        Toast.makeText(view.context, "${email.value} ${password.value}", Toast.LENGTH_SHORT).show()
    }

}