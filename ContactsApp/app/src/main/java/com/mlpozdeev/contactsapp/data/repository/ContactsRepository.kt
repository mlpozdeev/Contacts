package com.mlpozdeev.contactsapp.data.repository

import com.mlpozdeev.contactsapp.data.database.AppDatabase
import com.mlpozdeev.contactsapp.data.database.entity.ContactEntity
import com.mlpozdeev.contactsapp.data.network.api.ContactsApi
import com.mlpozdeev.contactsapp.data.toContact
import com.mlpozdeev.contactsapp.data.toContactEntity
import com.mlpozdeev.contactsapp.domain.model.Contact
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactsRepository @Inject constructor(
    private val api: ContactsApi,
    private val db: AppDatabase
) {

    fun refreshContacts(): Completable {
        return Single.concat(api.getContactsFrom1(), api.getContactsFrom2(), api.getContactsFrom3())
            .subscribeOn(Schedulers.io())
            .toList()
            .map { contactsLists ->
                val contacts: MutableList<ContactEntity> = mutableListOf()
                contactsLists.forEach {
                    val entities = it.map { contactDTO ->
                        contactDTO.toContactEntity()
                    }
                    contacts.addAll(entities)
                }

                return@map contacts.toList()
            }
            .flatMapCompletable {
                db.contactsDao().insertContacts(it)
            }
    }

    fun getAllContacts(): Flowable<List<Contact>> {
        return db.contactsDao().getAllContacts()
            .subscribeOn(Schedulers.io())
            .map { contacts ->
                contacts.map {
                    it.toContact()
                }
            }
    }
}