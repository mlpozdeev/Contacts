package com.mlpozdeev.domain.repository

import com.mlpozdeev.domain.model.Contact
import io.reactivex.Single

interface ContactRepository {
    fun loadContact(contactId: String): Single<Contact>
}