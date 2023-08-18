package com.anushka.didemo

import android.app.Application
import com.anushka.didemo.dagger2.DaggerSmartPhoneComponent
import com.anushka.didemo.dagger2.MemoryCardModule
import com.anushka.didemo.dagger2.SmartPhoneComponent

class SmartPhoneApp : Application() {

    lateinit var smartPhoneComponent: SmartPhoneComponent
    override fun onCreate() {
        super.onCreate()
        smartPhoneComponent = inItDagger()
    }

    private fun inItDagger(): SmartPhoneComponent {
        //DaggerSmartPhoneComponent.create().inject(this)
        return DaggerSmartPhoneComponent.builder()
            .memoryCardModule(MemoryCardModule(1000))
            .build()
    }
}