package com.mlpozdeev.contactsapp.di

import com.mlpozdeev.data.di.RepositoryModule
import com.mlpozdeev.domain.usecase.LoadContactUseCase
import com.mlpozdeev.domain.usecase.LoadContactsUseCase
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class, RepositoryModule::class])
interface ApplicationComponent {
    fun getLoadContactsUseCase(): LoadContactsUseCase

    fun getLoadContactUseCase(): LoadContactUseCase
}