package com.mlpozdeev.contactsapp.domain.usecase

import com.mlpozdeev.contactsapp.data.repository.ContactsRepository
import io.reactivex.Completable
import javax.inject.Inject

class RefreshContactsUseCase @Inject constructor(
    private val repository: ContactsRepository
) {
    fun refreshContacts(): Completable {
        return repository.refreshContacts()
    }
}