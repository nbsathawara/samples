package com.nbs.hiltdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nbs.hiltdemo.data.SmartPhone
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var smartPhone: SmartPhone


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        smartPhone.makeACallWithRecording()
    }
}
