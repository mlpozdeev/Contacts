package com.mlpozdeev.contactsapp.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "load_info")
data class LoadInfoEntity(
    @PrimaryKey
    @ColumnInfo(name = "last_refresh_date")
    val lastRefreshDate: Date
)
