package com.mlpozdeev.contactsapp.domain.repository

import com.mlpozdeev.contactsapp.domain.model.Contact
import io.reactivex.Single

interface ContactsRepository {
    fun loadContacts(isFromCache: Boolean): Single<List<Contact>>
}