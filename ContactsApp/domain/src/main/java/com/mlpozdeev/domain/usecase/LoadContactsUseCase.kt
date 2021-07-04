package com.mlpozdeev.domain.usecase

import com.mlpozdeev.entity.Contact
import com.mlpozdeev.domain.repository.ContactsRepository
import io.reactivex.Single
import javax.inject.Inject

class LoadContactsUseCase @Inject constructor(
    private val repository: ContactsRepository
) {
    fun loadContacts(isFromCache: Boolean): Single<List<Contact>> {
        return repository.loadContacts(isFromCache)
    }
}