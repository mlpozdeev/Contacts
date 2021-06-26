package com.mlpozdeev.contactsapp.data

import com.mlpozdeev.contactsapp.data.network.dto.ContactDTO
import com.mlpozdeev.contactsapp.data.network.dto.PeriodDTO
import com.mlpozdeev.contactsapp.data.network.dto.TemperamentDTO
import com.mlpozdeev.contactsapp.domain.model.Contact
import com.mlpozdeev.contactsapp.domain.model.Period
import com.mlpozdeev.contactsapp.domain.model.Temperament

fun ContactDTO.toContact(): Contact = Contact(
    id = id,
    name = name,
    phone = phone,
    height = height,
    biography = biography,
    temperament = temperament.toTemperament(),
    educationPeriod = educationPeriod.toPeriod()
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