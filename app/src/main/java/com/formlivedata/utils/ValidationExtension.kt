package com.formlivedata.utils

import android.content.Context
import android.util.Patterns
import com.formlivedata.R

class ValidationModel(val isValid: Boolean, val message: String)

fun String.validateEmail(context: Context): ValidationModel{
    if (this.trim().isEmpty()) {
        return  ValidationModel(false, context.getString(R.string.validation_email_empty))
    } else if (!Patterns.EMAIL_ADDRESS.matcher(this).matches()) {
        return  ValidationModel(false, context.getString(R.string.validation_email_not_valid))
    } else {
        return ValidationModel(true, "")
    }
}

fun String.validatePassword(context: Context): ValidationModel{
    if (this.isEmpty()) {
        return ValidationModel(false, context.getString(R.string.validation_password_empty))
    } else if (this.length < 5) {
        return ValidationModel(false, context.getString(R.string.validation_password_min))
    } else {
        return ValidationModel(true, "")
    }
}

fun String.validatePasswordConfirm(context: Context, password: String): ValidationModel{
    if (this.isEmpty()) {
        return ValidationModel(false, context.getString(R.string.validation_password_confirmation_empty))
    } else if (this.length != password.length) {
        return ValidationModel(false, context.getString(R.string.validation_password_confirmation_wrong))
    } else if (this != password){
        return ValidationModel(false, context.getString(R.string.validation_password_confirmation_wrong))
    }else {
        return ValidationModel(true, "")
    }
}