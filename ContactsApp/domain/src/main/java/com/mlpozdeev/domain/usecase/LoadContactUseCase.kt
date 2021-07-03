package com.mlpozdeev.domain.usecase

import com.mlpozdeev.domain.model.Contact
import com.mlpozdeev.domain.repository.ContactRepository
import io.reactivex.Single
import javax.inject.Inject

class LoadContactUseCase @Inject constructor(
    private val repository: ContactRepository
) {
    fun loadContact(contactId: String): Single<Contact> {
        return repository.loadContact(contactId)
    }
}