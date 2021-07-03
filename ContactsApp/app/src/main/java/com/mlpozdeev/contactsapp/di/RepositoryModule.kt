package com.mlpozdeev.contactsapp.di

import com.mlpozdeev.contactsapp.data.repository.ContactRepositoryImpl
import com.mlpozdeev.contactsapp.data.repository.ContactsRepositoryImpl
import com.mlpozdeev.domain.repository.ContactRepository
import com.mlpozdeev.domain.repository.ContactsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun getContactsRepository(repository: ContactsRepositoryImpl): ContactsRepository

    @Binds
    abstract fun getContactRepository(repository: ContactRepositoryImpl): ContactRepository
}