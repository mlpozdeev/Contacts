package com.mlpozdeev.contactsapp.data

import com.mlpozdeev.contactsapp.data.database.entity.ContactEntity
import com.mlpozdeev.contactsapp.data.network.dto.ContactDTO
import com.mlpozdeev.contactsapp.data.network.dto.TemperamentDTO
import com.mlpozdeev.contactsapp.domain.model.Contact
import com.mlpozdeev.contactsapp.domain.model.Period
import com.mlpozdeev.contactsapp.domain.model.Temperament

fun ContactDTO.toContactEntity(): ContactEntity = ContactEntity(
    id = id,
    name = name,
    phone = phone,
    height = height,
    biography = biography,
    temperament = temperament.toTemperament(),
    startEducationDate = educationPeriod.start,
    endEducationDate = educationPeriod.end
)

fun ContactEntity.toContact(): Contact = Contact(
    id = id,
    name = name,
    phone = phone,
    height = height,
    biography = biography,
    temperament = temperament,
    educationPeriod = Period(
        start = startEducationDate,
        end = endEducationDate
    )
)

fun TemperamentDTO.toTemperament(): Temperament = when (this) {
    TemperamentDTO.MELANCHOLIC -> Temperament.MELANCHOLIC
    TemperamentDTO.CHOLERIC -> Temperament.CHOLERIC
    TemperamentDTO.PHLEGMATIC -> Temperament.PHLEGMATIC
    TemperamentDTO.SANGUINE -> Temperament.SANGUINE
}