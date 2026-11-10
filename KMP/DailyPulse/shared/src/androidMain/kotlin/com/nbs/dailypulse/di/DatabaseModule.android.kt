package com.nbs.dailypulse.di

import com.nbs.dailypulse.db.DailyPulseDatabase
import com.nbs.dailypulse.db.getAndroidDatabaseBuilder
import com.nbs.dailypulse.db.getRoomDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val databaseModule: Module = module {
    single { getRoomDatabase(getAndroidDatabaseBuilder(get())) }
    single { get<DailyPulseDatabase>().articleDao }
}