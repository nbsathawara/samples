package com.nbs.hiltdemo.dagger2

import android.util.Log
import com.nbs.hiltdemo.Utils
import com.nbs.hiltdemo.data.MemoryCard
import dagger.Module
import dagger.Provides
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject


@Module
@InstallIn(SingletonComponent::class)
open class MemoryCardModule() {

    @Provides
    fun providesMemoryCard(): MemoryCard {
        Log.i("MyTAG", "Size of the memory is ${Utils.memorySize}")
        return MemoryCard(Utils.memorySize)
    }
}
