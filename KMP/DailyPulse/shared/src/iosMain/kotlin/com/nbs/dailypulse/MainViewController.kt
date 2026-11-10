package com.nbs.dailypulse

import androidx.compose.ui.window.ComposeUIViewController
import com.nbs.dailypulse.di.initKoin
import platform.UIKit.UIViewController

private var isKoinInitialized = false

fun MainViewController(): UIViewController {
    if (!isKoinInitialized) {
        initKoin {}
        isKoinInitialized = true
    }
    return ComposeUIViewController {
        App()
    }
}