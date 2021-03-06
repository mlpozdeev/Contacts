package com.mlpozdeev.data

import com.mlpozdeev.database.entity.ContactEntity
import com.mlpozdeev.network.dto.ContactDTO
import com.mlpozdeev.network.dto.PeriodDTO
import com.mlpozdeev.network.dto.TemperamentDTO
import com.mlpozdeev.database.entity.TemperamentEntity
import com.mlpozdeev.entity.Contact
import com.mlpozdeev.entity.Period
import com.mlpozdeev.entity.Temperament

fun Contact.toContactEntity(): ContactEntity =
    ContactEntity(
        id = id,
        name = name,
        phone = phone,
        height = height,
        biography = biography,
        temperament = temperament.toTemperamentEntity(),
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
    temperament = temperament.toTemperament(),
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

fun TemperamentEntity.toTemperament(): Temperament = when (this) {
    TemperamentEntity.MELANCHOLIC -> Temperament.MELANCHOLIC
    TemperamentEntity.CHOLERIC -> Temperament.CHOLERIC
    TemperamentEntity.PHLEGMATIC -> Temperament.PHLEGMATIC
    TemperamentEntity.SANGUINE -> Temperament.SANGUINE
}

fun Temperament.toTemperamentEntity(): TemperamentEntity = when (this) {
    Temperament.MELANCHOLIC -> TemperamentEntity.MELANCHOLIC
    Temperament.CHOLERIC -> TemperamentEntity.CHOLERIC
    Temperament.PHLEGMATIC -> TemperamentEntity.PHLEGMATIC
    Temperament.SANGUINE -> TemperamentEntity.SANGUINE
}

fun PeriodDTO.toPeriod(): Period = Period(
    start = start,
    end = end
)