package com.mlpozdeev.contactsapp.data.repository

import com.mlpozdeev.contactsapp.data.database.AppDatabase
import com.mlpozdeev.contactsapp.data.toContact
import com.mlpozdeev.contactsapp.domain.model.Contact
import com.mlpozdeev.contactsapp.domain.repository.ContactRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ContactRepositoryImpl @Inject constructor(
    private val db: AppDatabase
) : ContactRepository {
    override fun loadContact(contactId: String): Single<Contact> {
        return db.contactsDao().getContactById(contactId)
            .subscribeOn(Schedulers.io())
            .map {
                it.toContact()
            }
    }
}