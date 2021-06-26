package com.mlpozdeev.contactsapp.domain.usecase

import com.mlpozdeev.contactsapp.data.repository.ContactsRepository
import com.mlpozdeev.contactsapp.domain.model.Contact
import javax.inject.Inject

class LoadContactsUseCase @Inject constructor(
    private val repository: ContactsRepository
) {
    //test
    fun getContacts(): List<Contact> {
        return repository.getContacts(20)
    }
}