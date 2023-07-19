package com.example.smack.ui.utilities

import android.app.Activity
import android.content.Context
import android.inputmethodservice.InputMethodService
import android.view.inputmethod.InputMethodManager


fun hideKeyboard(activity: Activity) {
    val inputManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE)
            as InputMethodManager

    if (inputManager.isAcceptingText)
        inputManager.hideSoftInputFromWindow(activity.currentFocus?.windowToken, 0)

}