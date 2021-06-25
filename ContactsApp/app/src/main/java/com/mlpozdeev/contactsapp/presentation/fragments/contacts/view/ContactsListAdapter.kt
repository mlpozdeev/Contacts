package com.mlpozdeev.contactsapp.presentation.fragments.contacts.view

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mlpozdeev.contactsapp.presentation.fragments.contacts.model.ContactItem

class ContactsListAdapter : ListAdapter<ContactItem, ContactViewHolder>(ITEM_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<ContactItem>() {
            override fun areItemsTheSame(oldItem: ContactItem, newItem: ContactItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ContactItem, newItem: ContactItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}