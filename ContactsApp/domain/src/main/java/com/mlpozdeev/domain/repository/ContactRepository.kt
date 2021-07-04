package com.mlpozdeev.domain.repository

import com.mlpozdeev.entity.Contact
import io.reactivex.Single

interface ContactRepository {
    fun loadContact(contactId: String): Single<Contact>
}