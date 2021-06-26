package com.mlpozdeev.contactsapp.data.repository

import com.mlpozdeev.contactsapp.domain.model.Contact
import com.mlpozdeev.contactsapp.domain.model.Period
import com.mlpozdeev.contactsapp.domain.model.Temperament
import java.util.*
import javax.inject.Inject

class ContactsRepository @Inject constructor() {
    //test
    fun getContacts(count: Int): List<Contact> {
        return generateContacts(count)
    }

    private fun generateContacts(count: Int): List<Contact> {
        val contacts: MutableList<Contact> = mutableListOf()
        for (i in 0..count) {
            contacts.add(
                Contact(
                    id = i.toString(),
                    name = "Name$i",
                    phone = i.toString(),
                    height = i.toFloat(),
                    biography = "",
                    temperament = Temperament.CHOLERIC,
                    educationPeriod = Period(
                        start = Date(),
                        end = Date()
                    )
                )
            )
        }

        return contacts
    }
}