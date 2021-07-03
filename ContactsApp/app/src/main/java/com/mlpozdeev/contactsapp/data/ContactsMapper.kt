package com.mlpozdeev.contactsapp.data

import com.mlpozdeev.contactsapp.data.database.entity.ContactEntity
import com.mlpozdeev.contactsapp.data.network.dto.ContactDTO
import com.mlpozdeev.contactsapp.data.network.dto.PeriodDTO
import com.mlpozdeev.contactsapp.data.network.dto.TemperamentDTO
import com.mlpozdeev.domain.model.Contact
import com.mlpozdeev.domain.model.Period
import com.mlpozdeev.domain.model.Temperament

fun Contact.toContactEntity(): ContactEntity = ContactEntity(
    id = id,
    name = name,
    phone = phone,
    height = height,
    biography = biography,
    temperament = temperament,
    startEducationDate = educationPeriod.start,
    endEducationDate = educationPeriod.end
)

fun ContactDTO.toContact(): Contact = Contact(
    id = id,
    name = name,
    phone = phone,
    height = height,
    biography = biography,
    temperament = temperament.toTemperament(),
    educationPeriod = educationPeriod.toPeriod()
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

fun PeriodDTO.toPeriod(): Period = Period(
    start = start,
    end = end
)