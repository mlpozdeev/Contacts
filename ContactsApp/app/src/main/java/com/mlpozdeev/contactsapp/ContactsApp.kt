package com.mlpozdeev.contactsapp

import android.app.Application
import com.mlpozdeev.contactsapp.di.ApplicationComponent
import com.mlpozdeev.contactsapp.di.DaggerApplicationComponent

class ContactsApp : Application() {
    lateinit var appComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.create()
    }
}