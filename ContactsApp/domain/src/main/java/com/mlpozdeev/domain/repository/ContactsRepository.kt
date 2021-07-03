package com.mlpozdeev.domain.repository

import com.mlpozdeev.domain.model.Contact
import io.reactivex.Single

interface ContactsRepository {
    fun loadContacts(isFromCache: Boolean): Single<List<Contact>>
}