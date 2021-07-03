package com.mlpozdeev.contactsapp.presentation.fragments

import com.mlpozdeev.domain.model.Contact
import com.mlpozdeev.contactsapp.presentation.fragments.contacts.model.ContactItem
import com.mlpozdeev.contactsapp.presentation.fragments.profile.model.Profile
import java.text.SimpleDateFormat
import java.util.*

fun Contact.toContactItem(): ContactItem = ContactItem(
    id = id,
    name = name,
    height = height.toString(),
    phoneNumber = phone
)

fun Contact.toProfile(): Profile = Profile(
    id = id,
    name = name,
    phoneNumber = phone,
    temperament = temperament.toString(),
    educationPeriod = "${educationPeriod.start.toSimpleDateString()} - ${educationPeriod.end.toSimpleDateString()}",
    biography = biography
)

fun Date.toSimpleDateString(): String {
    val pattern = "dd.MM.yyyy"
    val simpleDateFormat = SimpleDateFormat(pattern, Locale.ROOT)

    return simpleDateFormat.format(this)
}