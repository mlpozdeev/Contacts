package com.mlpozdeev.contactsapp.domain.usecase

import com.mlpozdeev.contactsapp.data.repository.ContactsRepository
import com.mlpozdeev.contactsapp.domain.model.Contact
import io.reactivex.Single
import javax.inject.Inject

class LoadContactUseCase @Inject constructor(
    private val repository: ContactsRepository
) {
    fun loadContact(contactId: String): Single<Contact> {
        return repository.loadContact(contactId)
    }
}