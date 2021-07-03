package com.mlpozdeev.contactsapp.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mlpozdeev.domain.model.Temperament
import java.util.*

@Entity(tableName = "contact")
data class ContactEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "phone")
    val phone: String,
    @ColumnInfo(name = "height")
    val height: Float,
    @ColumnInfo(name = "biography")
    val biography: String,
    @ColumnInfo(name = "temperament")
    val temperament: Temperament,
    @ColumnInfo(name = "start_education_date")
    val startEducationDate: Date,
    @ColumnInfo(name = "end_education_date")
    val endEducationDate: Date
)
