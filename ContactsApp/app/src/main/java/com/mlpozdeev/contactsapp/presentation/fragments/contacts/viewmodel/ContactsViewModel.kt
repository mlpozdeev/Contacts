package com.mlpozdeev.contactsapp.presentation.fragments.contacts.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mlpozdeev.contactsapp.domain.usecase.GetContactsUseCase
import com.mlpozdeev.contactsapp.domain.usecase.RefreshContactsUseCase
import com.mlpozdeev.contactsapp.presentation.fragments.contacts.model.ContactItem
import com.mlpozdeev.contactsapp.presentation.fragments.toContactItem
import io.reactivex.disposables.CompositeDisposable

class ContactsViewModel(
    private val refreshContactsUseCase: RefreshContactsUseCase,
    private val getContactsUseCase: GetContactsUseCase
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val mutableContactsLiveData: MutableLiveData<List<ContactItem>> = MutableLiveData()

    val contactsLiveData: LiveData<List<ContactItem>> = mutableContactsLiveData

    init {
        refreshData()
        loadData()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun refreshData() {
        val refreshDisposable = refreshContactsUseCase.refreshContacts()
            .subscribe({
                Log.d(TAG, "Refresh completed")
            }, {
                Log.e(TAG, it.message ?: "Error")
            })

        compositeDisposable.add(refreshDisposable)
    }

    private fun loadData() {
        val loadDisposable = getContactsUseCase.getAllContacts()
            .map { contacts ->
                contacts.map {
                    it.toContactItem()
                }
            }
            .subscribe({
                mutableContactsLiveData.postValue(it)
            }, {
                Log.e(TAG, it.message ?: "Error")
            })

        compositeDisposable.add(loadDisposable)
    }

    companion object {
        private const val TAG = "ContactsViewModel"
    }
}