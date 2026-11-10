package com.nbs.dailypulse.di

import com.nbs.dailypulse.db.DailyPulseDatabase
import com.nbs.dailypulse.db.getIosDatabaseBuilder
import com.nbs.dailypulse.db.getRoomDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val databaseModule: Module = module {
    single { getRoomDatabase(getIosDatabaseBuilder()) }
    single { get<DailyPulseDatabase>().articleDao }
}