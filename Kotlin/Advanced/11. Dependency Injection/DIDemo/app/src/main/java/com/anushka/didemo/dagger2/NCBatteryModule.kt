package com.anushka.didemo.dagger2

import com.anushka.didemo.data.Battery
import com.anushka.didemo.data.NickelBattery
import dagger.Binds
import dagger.Module

@Module
abstract class NCBatteryModule {
    @Binds
    abstract fun bindsNCBattery(nickelBattery: NickelBattery): Battery
}