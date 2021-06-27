package com.mlpozdeev.contactsapp.data.database.dao

import androidx.room.*
import com.mlpozdeev.contactsapp.data.database.AppDatabase
import com.mlpozdeev.contactsapp.data.database.entity.ContactEntity
import com.mlpozdeev.contactsapp.data.database.entity.LoadInfoEntity
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

@Dao
abstract class ContactsDao(private val db: AppDatabase) {
    @Query("SELECT * FROM contact")
    abstract fun getAllContacts(): Single<List<ContactEntity>>

    fun insertContacts(contacts: List<ContactEntity>, loadInfo: LoadInfoEntity): Completable {
        return Completable
            .fromAction { insertContactsTransaction(contacts, loadInfo) }
            .subscribeOn(Schedulers.from(db.queryExecutor))
    }

    @Transaction
    open fun insertContactsTransaction(contacts: List<ContactEntity>, loadInfo: LoadInfoEntity) {
        deleteLoadInfo()
        insertLoadInfo(loadInfo)
        insertContacts(contacts)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertContacts(contacts: List<ContactEntity>)

    @Query("SELECT * FROM load_info LIMIT 1")
    abstract fun getLastLoadInfo(): Single<LoadInfoEntity>

    @Query("DELETE FROM load_info")
    abstract fun deleteLoadInfo()

    @Insert
    abstract fun insertLoadInfo(loadInfo: LoadInfoEntity)
}