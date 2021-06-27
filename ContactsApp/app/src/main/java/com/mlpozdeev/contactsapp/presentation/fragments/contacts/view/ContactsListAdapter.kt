package com.mlpozdeev.contactsapp.presentation.fragments.contacts.view

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mlpozdeev.contactsapp.presentation.fragments.contacts.model.ContactItem

class ContactsListAdapter(
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<ContactViewHolder>() {

    private val differ: AsyncListDiffer<ContactItem> = AsyncListDiffer(this, ITEM_COMPARATOR)

    private var originalItems: List<ContactItem>? = null

    private var currentSearchString = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder.create(parent) {
            onItemClick(
                differ.currentList[it].id
            )
        }
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
        differ.submitList(newItems.filter { item ->
            isItemInSearch(item)
        })
    }

    private fun submitList() {
        originalItems?.let { items ->
            differ.submitList(items.filter { item ->
                isItemInSearch(item)
            })
        }
    }

    private fun isItemInSearch(item: ContactItem): Boolean {
        return item.name.startsWith(currentSearchString, true)
                || item.phoneNumber
            .filter { it != ' ' && it != '(' && it != ')' && it != '+' && it != '-' }
            .startsWith(currentSearchString
                .filter { it != ' ' && it != '(' && it != ')' && it != '+' && it != '-'  })
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