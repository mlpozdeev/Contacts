package com.mlpozdeev.contactsapp.presentation.fragments.profile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mlpozdeev.domain.usecase.LoadContactUseCase
import com.mlpozdeev.contactsapp.presentation.SingleLiveEvent
import com.mlpozdeev.contactsapp.presentation.fragments.profile.model.Profile
import com.mlpozdeev.contactsapp.presentation.fragments.toProfile
import io.reactivex.disposables.CompositeDisposable

class ProfileViewModel(
    private val contactId: String,
    private val loadContactUseCase: LoadContactUseCase
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val mutableProfileLiveData: MutableLiveData<Profile> = MutableLiveData()
    private val clickedPhoneNumber: SingleLiveEvent<String> = SingleLiveEvent()

    val profileLiveData: LiveData<Profile> = mutableProfileLiveData

    init {
        load()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun phoneNumberClicked() {
        mutableProfileLiveData.value?.let {
            clickedPhoneNumber.value = it.phoneNumber
        }
    }

    fun getClickedPhoneNumber(): SingleLiveEvent<String> {
        return clickedPhoneNumber
    }

    private fun load() {
        val loadDisposable = loadContactUseCase.loadContact(contactId)
            .subscribe({
                mutableProfileLiveData.postValue(it.toProfile())
            }, {
                throw it
            })

        compositeDisposable.add(loadDisposable)
    }
}