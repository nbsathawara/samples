package com.nbs.chatroomapp.di

import com.nbs.chatroomapp.repository.AccountRepository
import com.nbs.chatroomapp.repository.AccountRepositoryImpl
import com.nbs.chatroomapp.repository.ChatRoomRepository
import com.nbs.chatroomapp.repository.ChatRoomRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    @Singleton
    abstract fun provideAccountService(impl: AccountRepositoryImpl): AccountRepository

    @Binds
    @Singleton
    abstract fun provideChatRoomService(impl: ChatRoomRepositoryImpl): ChatRoomRepository
}