package com.mlpozdeev.contactsapp.di

import com.mlpozdeev.database.di.DatabaseModule
import com.mlpozdeev.contactsapp.data.network.di.NetworkModule
import com.mlpozdeev.domain.usecase.LoadContactsUseCase
import com.mlpozdeev.domain.usecase.LoadContactUseCase
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class, ContextModule::class, RepositoryModule::class])
interface ApplicationComponent {
    fun getLoadContactsUseCase(): LoadContactsUseCase

    fun getLoadContactUseCase(): LoadContactUseCase
}