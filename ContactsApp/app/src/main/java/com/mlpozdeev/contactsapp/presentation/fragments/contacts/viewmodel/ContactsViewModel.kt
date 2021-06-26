package com.mlpozdeev.contactsapp.presentation.fragments.contacts.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mlpozdeev.contactsapp.domain.usecase.LoadContactsUseCase
import com.mlpozdeev.contactsapp.presentation.fragments.contacts.model.ContactItem
import com.mlpozdeev.contactsapp.presentation.fragments.toContactItem

class ContactsViewModel(
    private val loadContactsUseCase: LoadContactsUseCase
) : ViewModel() {

    private val mutableContactsLiveData: MutableLiveData<List<ContactItem>> = MutableLiveData()

    val contactsLiveData: LiveData<List<ContactItem>> = mutableContactsLiveData

    init {
        load()
    }

    private fun load() {
        mutableContactsLiveData.value = loadContactsUseCase.getContacts().map {
            it.toContactItem()
        }
    }
}