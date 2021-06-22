package com.formlivedata.utils

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("errorText")
fun TextInputLayout.setErrorMessage(errorMessage: String?) {
    this.error = errorMessage
}