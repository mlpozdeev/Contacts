package com.mlpozdeev.contactsapp.domain.usecase

import com.mlpozdeev.contactsapp.data.repository.ContactsRepository
import com.mlpozdeev.contactsapp.domain.model.Contact
import io.reactivex.Flowable
import javax.inject.Inject

class GetContactsUseCase @Inject constructor(
    private val repository: ContactsRepository
) {

    fun getAllContacts(): Flowable<List<Contact>> {
        return repository.getAllContacts()
    }
}