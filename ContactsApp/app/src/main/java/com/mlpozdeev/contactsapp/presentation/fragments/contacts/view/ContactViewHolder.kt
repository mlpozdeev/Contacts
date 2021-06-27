package com.mlpozdeev.contactsapp.presentation.fragments.contacts.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mlpozdeev.contactsapp.R
import com.mlpozdeev.contactsapp.presentation.fragments.contacts.model.ContactItem

class ContactViewHolder(
    view: View,
    onItemClick: (Int) -> Unit
) : RecyclerView.ViewHolder(view) {

    private val nameTextView: TextView = view.findViewById(R.id.text_view_contact_name)
    private val heightTextView: TextView = view.findViewById(R.id.text_view_contact_height)
    private val phoneNumberTextView: TextView = view.findViewById(R.id.text_view_contact_phone_number)

    init {
        itemView.setOnClickListener {
            onItemClick(adapterPosition)
        }
    }

    fun bind(item: ContactItem) {
        nameTextView.text = item.name
        heightTextView.text = item.height
        phoneNumberTextView.text = item.phoneNumber
    }

    companion object {
        fun create(parent: ViewGroup, onItemClick: (Int) -> Unit): ContactViewHolder {
            val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_contact, parent, false)

            return ContactViewHolder(view, onItemClick)
        }
    }
}