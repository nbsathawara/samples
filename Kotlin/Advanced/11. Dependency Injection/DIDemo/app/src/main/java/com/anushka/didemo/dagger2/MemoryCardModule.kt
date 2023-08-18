package com.anushka.didemo.dagger2

import android.util.Log
import com.anushka.didemo.data.MemoryCard
import dagger.Module
import dagger.Provides

@Module
class MemoryCardModule(private val memorySize: Int) {
    @Provides
    fun providesMemoryCard(): MemoryCard {
        Log.i("MyTAG", "Size of the memory is $memorySize")
        return MemoryCard()
    }
}