package com.anushka.didemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anushka.didemo.data.SmartPhone
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var smartPhone: SmartPhone
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as SmartPhoneApp).smartPhoneComponent.inject(this)
        smartPhone.makeACallWithRecording()
    }
}