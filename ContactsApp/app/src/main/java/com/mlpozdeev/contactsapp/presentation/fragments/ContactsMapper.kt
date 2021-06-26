package com.mlpozdeev.contactsapp.presentation.fragments

import com.mlpozdeev.contactsapp.domain.model.Contact
import com.mlpozdeev.contactsapp.presentation.fragments.contacts.model.ContactItem

fun Contact.toContactItem(): ContactItem = ContactItem(
    id = id,
    name = name,
    height = height.toString(),
    phoneNumber = phone
)