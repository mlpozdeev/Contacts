package com.mlpozdeev.network.api

import com.mlpozdeev.network.dto.ContactDTO
import io.reactivex.Single
import retrofit2.http.GET

interface ContactsApi {
    @GET("./SkbkonturMobile/mobile-test-droid/master/json/generated-01.json")
    fun getContactsFrom1(): Single<List<ContactDTO>>

    @GET("./SkbkonturMobile/mobile-test-droid/master/json/generated-02.json")
    fun getContactsFrom2(): Single<List<ContactDTO>>

    @GET("./SkbkonturMobile/mobile-test-droid/master/json/generated-03.json")
    fun getContactsFrom3(): Single<List<ContactDTO>>
}