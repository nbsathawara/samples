package com.nbs.chatroomapp.di

import com.nbs.chatroomapp.repository.AccountRepository
import com.nbs.chatroomapp.repository.AccountRepositoryImpl
import com.nbs.chatroomapp.repository.ChatRoomRepository
import com.nbs.chatroomapp.repository.ChatRoomRepositoryImpl
import com.nbs.chatroomapp.repository.MessageRepository
import com.nbs.chatroomapp.repository.MessageRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideAccountRepository(impl: AccountRepositoryImpl): AccountRepository

    @Binds
    @Singleton
    abstract fun provideChatRoomRepository(impl: ChatRoomRepositoryImpl): ChatRoomRepository

    @Binds
    @Singleton
    abstract fun provideMessageRepository(impl: MessageRepositoryImpl): MessageRepository
}