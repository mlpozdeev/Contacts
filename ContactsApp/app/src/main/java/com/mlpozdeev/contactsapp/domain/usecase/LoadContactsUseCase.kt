package com.mlpozdeev.contactsapp.domain.usecase

import com.mlpozdeev.contactsapp.data.repository.ContactsRepository
import com.mlpozdeev.contactsapp.domain.model.Contact
import io.reactivex.Single
import javax.inject.Inject

class LoadContactsUseCase @Inject constructor(
    private val repository: ContactsRepository
) {

    fun loadContacts(isFromCache: Boolean): Single<List<Contact>> {
        return repository.loadContacts(isFromCache)
    }
}