package com.mlpozdeev.contactsapp.presentation.fragments.contacts.view

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mlpozdeev.contactsapp.presentation.fragments.contacts.model.ContactItem

class ContactsListAdapter : RecyclerView.Adapter<ContactViewHolder>() {

    private val differ: AsyncListDiffer<ContactItem> = AsyncListDiffer(this, ITEM_COMPARATOR)

    private var originalItems: List<ContactItem>? = null

    private var currentSearchString = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun filter(searchString: String) {
        currentSearchString = searchString
        submitList()
    }

    fun submitList(newItems: List<ContactItem>) {
        originalItems = newItems
        submitList()
    }

    private fun submitList() {
        originalItems?.let { items ->
            differ.submitList(items.filter {
                it.name.startsWith(currentSearchString, true)
            })
        }
    }

    companion object {
        private const val TAG = "ContactsListAdapter"

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