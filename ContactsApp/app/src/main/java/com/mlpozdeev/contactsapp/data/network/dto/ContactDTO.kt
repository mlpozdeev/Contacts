package com.mlpozdeev.contactsapp.data.network.dto

import com.mlpozdeev.contactsapp.domain.model.Period

data class ContactDTO(
    val id: String,
    val name: String,
    val phone: String,
    val height: Float,
    val biography: String,
    val temperament: TemperamentDTO,
    val educationPeriod: Period
)