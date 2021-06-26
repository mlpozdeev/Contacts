package com.mlpozdeev.contactsapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mlpozdeev.contactsapp.data.database.entity.ContactEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface ContactsDao {
    @Query("SELECT * FROM contact")
    fun getAllContacts(): Flowable<List<ContactEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContacts(contacts: List<ContactEntity>): Completable
}