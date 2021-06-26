package com.mlpozdeev.contactsapp.presentation.fragments.contacts.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mlpozdeev.contactsapp.domain.usecase.LoadContactsUseCase
import com.mlpozdeev.contactsapp.presentation.fragments.contacts.model.ContactItem
import com.mlpozdeev.contactsapp.presentation.fragments.toContactItem
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ContactsViewModel(
    private val loadContactsUseCase: LoadContactsUseCase
) : ViewModel() {

    //test
    private lateinit var disposable: Disposable

    private val mutableContactsLiveData: MutableLiveData<List<ContactItem>> = MutableLiveData()

    val contactsLiveData: LiveData<List<ContactItem>> = mutableContactsLiveData

    init {
        Log.d(TAG, "Load started")
        load()
    }

    private fun load() {
        disposable = loadContactsUseCase.getContacts()
            .subscribeOn(Schedulers.io())
            .map { contacts ->
                contacts.map {
                    it.toContactItem()
                }
            }
            .subscribe({
                mutableContactsLiveData.postValue(it)
                Log.d(TAG, "Load ended")
            }, {
                Log.e(TAG, it.message ?: "Error")
            })
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

    companion object {
        private const val TAG = "ContactsViewModel"
    }
}