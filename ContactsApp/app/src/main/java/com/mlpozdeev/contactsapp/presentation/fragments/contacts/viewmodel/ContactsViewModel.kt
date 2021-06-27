package com.mlpozdeev.contactsapp.presentation.fragments.contacts.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mlpozdeev.contactsapp.domain.usecase.LoadContactsUseCase
import com.mlpozdeev.contactsapp.presentation.fragments.contacts.model.ContactItem
import com.mlpozdeev.contactsapp.presentation.fragments.toContactItem
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ContactsViewModel(
    private val loadContactsUseCase: LoadContactsUseCase
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val mutableContactsLiveData: MutableLiveData<List<ContactItem>> = MutableLiveData()
    private val mutableIsLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData()

    val contactsLiveData: LiveData<List<ContactItem>> = mutableContactsLiveData
    val isLoadingLiveData: LiveData<Boolean> = mutableIsLoadingLiveData

    init {
        initialLoadData()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
        Log.d(TAG, "ContactsViewModel cleared")
    }

    fun refreshData() {
        val refreshDisposable = loadContactsUseCase.loadContacts(isFromCache = false)
            .observeOn(Schedulers.computation())
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

        compositeDisposable.add(refreshDisposable)
    }

    private fun initialLoadData() {
        val loadDisposable = loadContactsUseCase.loadContacts(isFromCache = true)
            .observeOn(Schedulers.computation())
            .map { contacts ->
                contacts.map {
                    it.toContactItem()
                }
            }
            .doOnSubscribe {
                mutableIsLoadingLiveData.postValue(true)
            }
            .subscribe({
                mutableContactsLiveData.postValue(it)
                mutableIsLoadingLiveData.postValue(false)
            }, {
                Log.e(TAG, it.message ?: "Error")
            })

        compositeDisposable.add(loadDisposable)
    }

    companion object {
        private const val TAG = "ContactsViewModel"
    }
}