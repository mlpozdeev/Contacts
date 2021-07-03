package com.mlpozdeev.data.repository

import com.mlpozdeev.data.toContact
import com.mlpozdeev.database.AppDatabase
import com.mlpozdeev.domain.model.Contact
import com.mlpozdeev.domain.repository.ContactRepository
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