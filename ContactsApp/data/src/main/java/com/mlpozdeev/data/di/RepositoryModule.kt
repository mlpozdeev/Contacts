package com.mlpozdeev.data.di

import com.mlpozdeev.data.repository.ContactRepositoryImpl
import com.mlpozdeev.data.repository.ContactsRepositoryImpl
import com.mlpozdeev.database.di.DatabaseModule
import com.mlpozdeev.domain.repository.ContactRepository
import com.mlpozdeev.domain.repository.ContactsRepository
import com.mlpozdeev.network.di.NetworkModule
import dagger.Binds
import dagger.Module

@Module(includes = [DatabaseModule::class, NetworkModule::class])
abstract class RepositoryModule {
    @Binds
    abstract fun getContactsRepository(repository: ContactsRepositoryImpl): ContactsRepository

    @Binds
    abstract fun getContactRepository(repository: ContactRepositoryImpl): ContactRepository
}