package com.mlpozdeev.contactsapp.di

import com.mlpozdeev.contactsapp.data.network.di.NetworkModule
import com.mlpozdeev.contactsapp.domain.usecase.LoadContactsUseCase
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {
    fun getLoadContactsUseCase(): LoadContactsUseCase
}