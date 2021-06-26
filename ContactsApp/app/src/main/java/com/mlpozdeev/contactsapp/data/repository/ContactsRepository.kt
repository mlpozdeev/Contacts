package com.mlpozdeev.contactsapp.data.repository

import com.mlpozdeev.contactsapp.data.network.api.ContactsApi
import com.mlpozdeev.contactsapp.data.toContact
import com.mlpozdeev.contactsapp.domain.model.Contact
import io.reactivex.Single
import javax.inject.Inject

class ContactsRepository @Inject constructor(
    private val api: ContactsApi
) {
    //test
    fun getContacts(): Single<List<Contact>> {
        return Single.concat(api.getContactsFrom1(), api.getContactsFrom2(), api.getContactsFrom3())
            .map { contacts ->
                contacts.map {
                    it.toContact()
                }
            }
            .toList()
            .map { contactsLists ->
                val contacts: MutableList<Contact> = mutableListOf()
                contactsLists.forEach {
                    contacts.addAll(it)
                }

                return@map contacts
            }
    }
}