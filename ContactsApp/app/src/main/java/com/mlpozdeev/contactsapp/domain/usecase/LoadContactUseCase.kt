package com.mlpozdeev.contactsapp.domain.usecase

import com.mlpozdeev.contactsapp.domain.model.Contact
import com.mlpozdeev.contactsapp.domain.repository.ContactRepository
import io.reactivex.Single
import javax.inject.Inject

class LoadContactUseCase @Inject constructor(
    private val repository: ContactRepository
) {
    fun loadContact(contactId: String): Single<Contact> {
        return repository.loadContact(contactId)
    }
}