package com.mlpozdeev.contactsapp.di

import com.mlpozdeev.contactsapp.domain.usecase.LoadContactsUseCase
import dagger.Component

@Component
interface ApplicationComponent {
    fun getLoadContactsUseCase(): LoadContactsUseCase
}