package com.nbs.dailypulse.di

import com.nbs.dailypulse.articles.ArticlesViewModel
import com.nbs.dailypulse.services.ArticleService
import com.nbs.dailypulse.usecases.ArticleUseCase
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import org.koin.mp.KoinPlatformTools

expect val databaseModule: Module

val networkModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }
}

val articlesModule = module {
    singleOf(::ArticleService)
    singleOf(::ArticleUseCase)
    factoryOf(::ArticlesViewModel)
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    if (KoinPlatformTools.defaultContext().getOrNull() == null) {
        startKoin {
            appDeclaration()
            modules(networkModule, databaseModule, articlesModule)         }
    }
}