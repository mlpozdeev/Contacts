package com.mlpozdeev.contactsapp.domain.repository

import com.mlpozdeev.contactsapp.domain.model.Contact
import io.reactivex.Single

interface ContactRepository {
    fun loadContact(contactId: String): Single<Contact>
}