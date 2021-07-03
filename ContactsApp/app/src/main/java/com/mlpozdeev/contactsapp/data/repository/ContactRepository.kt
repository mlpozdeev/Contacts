package com.mlpozdeev.contactsapp.data.repository

import com.mlpozdeev.contactsapp.data.database.AppDatabase
import com.mlpozdeev.contactsapp.data.toContact
import com.mlpozdeev.contactsapp.domain.model.Contact
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ContactRepository @Inject constructor(
    private val db: AppDatabase
) {
    fun loadContact(contactId: String): Single<Contact> {
        return db.contactsDao().getContactById(contactId)
            .subscribeOn(Schedulers.io())
            .map {
                it.toContact()
            }
    }
}