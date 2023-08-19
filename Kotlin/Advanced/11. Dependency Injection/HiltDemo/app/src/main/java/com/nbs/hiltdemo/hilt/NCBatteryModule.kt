package com.nbs.hiltdemo.dagger2

import com.nbs.hiltdemo.data.Battery
import com.nbs.hiltdemo.data.NickelBattery
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NCBatteryModule {
    @Binds
    abstract fun bindsNCBattery(nickelBattery: NickelBattery): Battery
}