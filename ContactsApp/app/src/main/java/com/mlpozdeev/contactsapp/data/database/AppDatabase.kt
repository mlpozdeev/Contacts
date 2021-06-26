package com.mlpozdeev.contactsapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mlpozdeev.contactsapp.data.database.dao.ContactsDao
import com.mlpozdeev.contactsapp.data.database.entity.ContactEntity

@Database(entities = [ContactEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun contactsDao(): ContactsDao

    companion object {
        const val DB_NAME = "contacts.db"
    }
}