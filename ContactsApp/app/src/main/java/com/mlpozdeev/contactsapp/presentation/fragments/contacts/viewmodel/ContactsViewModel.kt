package com.mlpozdeev.contactsapp.presentation.fragments.contacts.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mlpozdeev.contactsapp.presentation.fragments.contacts.model.ContactItem

class ContactsViewModel : ViewModel() {

    private val mutableContactsLiveData: MutableLiveData<List<ContactItem>> = MutableLiveData()

    val contactsLiveData: LiveData<List<ContactItem>> = mutableContactsLiveData

    init {
        load()
    }

    private fun load() {
        //test
        val list = listOf(
            ContactItem(
                id = 0,
                name = "Carol Rodriguez",
                height = "195.3",
                phoneNumber = "+7 (828) 461-2624"
            ),
            ContactItem(
                id = 0,
                name = "Carol Rodriguez",
                height = "195.3",
                phoneNumber = "+7 (828) 461-2624"
            ),
            ContactItem(
                id = 0,
                name = "Carol Rodriguez",
                height = "195.3",
                phoneNumber = "+7 (828) 461-2624"
            ),
            ContactItem(
                id = 0,
                name = "Carol Rodriguez",
                height = "195.3",
                phoneNumber = "+7 (828) 461-2624"
            ),
            ContactItem(
                id = 0,
                name = "Carol Rodriguez",
                height = "195.3",
                phoneNumber = "+7 (828) 461-2624"
            ),
            ContactItem(
                id = 0,
                name = "Carol Rodriguez",
                height = "195.3",
                phoneNumber = "+7 (828) 461-2624"
            ),
            ContactItem(
                id = 0,
                name = "Carol Rodriguez",
                height = "195.3",
                phoneNumber = "+7 (828) 461-2624"
            ),
            ContactItem(
                id = 0,
                name = "Carol Rodriguez",
                height = "195.3",
                phoneNumber = "+7 (828) 461-2624"
            ),
            ContactItem(
                id = 0,
                name = "Carol Rodriguez",
                height = "195.3",
                phoneNumber = "+7 (828) 461-2624"
            ),
            ContactItem(
                id = 0,
                name = "Carol Rodriguez",
                height = "195.3",
                phoneNumber = "+7 (828) 461-2624"
            )
        )

        mutableContactsLiveData.value = list
    }
}