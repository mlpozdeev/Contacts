package com.mlpozdeev.contactsapp.di

import com.mlpozdeev.contactsapp.data.database.di.DatabaseModule
import com.mlpozdeev.contactsapp.data.network.di.NetworkModule
import com.mlpozdeev.contactsapp.domain.usecase.LoadContactsUseCase
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class, ContextModule::class])
interface ApplicationComponent {
    fun getLoadContactsUseCase(): LoadContactsUseCase
}