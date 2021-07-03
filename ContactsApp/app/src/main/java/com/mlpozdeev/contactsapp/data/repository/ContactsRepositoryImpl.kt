package com.mlpozdeev.contactsapp.data.repository

import android.util.Log
import androidx.room.EmptyResultSetException
import com.mlpozdeev.contactsapp.data.database.AppDatabase
import com.mlpozdeev.contactsapp.data.database.entity.LoadInfoEntity
import com.mlpozdeev.contactsapp.data.network.api.ContactsApi
import com.mlpozdeev.contactsapp.data.toContact
import com.mlpozdeev.contactsapp.data.toContactEntity
import com.mlpozdeev.contactsapp.domain.model.Contact
import com.mlpozdeev.contactsapp.domain.repository.ContactsRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class ContactsRepositoryImpl @Inject constructor(
    private val api: ContactsApi,
    private val db: AppDatabase
) : ContactsRepository {

    override fun loadContacts(isFromCache: Boolean): Single<List<Contact>> {
        return if (isFromCache) {
            loadContacts()
        } else {
            loadContactsFromServer()
        }
    }

    private fun loadContacts(): Single<List<Contact>> {
        return db.contactsDao().getLastLoadInfo()
            .subscribeOn(Schedulers.io())
            .flatMap { loadInfo ->
                if (loadInfo.lastRefreshDate.time + CACHE_EXPIRATION_TIME_MILLISECONDS < Date().time) {
                    loadContactsFromServer()
                } else {
                    Log.d(TAG, "Get data from DB")
                    db.contactsDao().getAllContacts()
                        .observeOn(Schedulers.computation())
                        .map { contacts ->
                            contacts.map {
                                it.toContact()
                            }
                        }
                }
            }
            .onErrorResumeNext {
                if (it is EmptyResultSetException) {
                    loadContactsFromServer()
                } else {
                    Single.error(it)
                }
            }
    }

    private fun loadContactsFromServer(): Single<List<Contact>> {
        Log.d(TAG, "Get data from server")
        return Single.concat(api.getContactsFrom1(), api.getContactsFrom2(), api.getContactsFrom3())
            .subscribeOn(Schedulers.io())
            .toList()
            .observeOn(Schedulers.computation())
            .map { contactsLists ->
                val contacts: MutableList<Contact> = mutableListOf()
                contactsLists.forEach {
                    contacts.addAll(it.map { contactDTO ->
                        contactDTO.toContact()
                    })
                }

                return@map contacts.toList()
            }
            .observeOn(Schedulers.io())
            .flatMap { contacts ->
                val entities = contacts.map {
                    it.toContactEntity()
                }
                db.contactsDao().insertContacts(entities, LoadInfoEntity(Date()))
                    .toSingle { contacts }
            }
    }

    companion object {
        private const val TAG = "ContactsRepository"
        private const val CACHE_EXPIRATION_TIME_MILLISECONDS = 60_000
    }
}