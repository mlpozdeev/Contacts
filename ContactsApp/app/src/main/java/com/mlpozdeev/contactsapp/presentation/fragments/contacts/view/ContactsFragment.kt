package com.mlpozdeev.contactsapp.presentation.fragments.contacts.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.mlpozdeev.contactsapp.ContactsApp
import com.mlpozdeev.contactsapp.R
import com.mlpozdeev.contactsapp.databinding.FragmentContactsBinding
import com.mlpozdeev.contactsapp.presentation.fragments.contacts.viewmodel.ContactsViewModel

class ContactsFragment : Fragment() {

    private lateinit var viewModel: ContactsViewModel

    private var _binding: FragmentContactsBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val loadContactsUseCase = (requireActivity().application as ContactsApp).appComponent
            .getLoadContactsUseCase()

        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return ContactsViewModel(
                    loadContactsUseCase
                ) as T
            }
        }).get(ContactsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = ContactsListAdapter()
        createContactsList(adapter)

        binding.swipeRefreshLayoutContacts.setOnRefreshListener {
            viewModel.refreshData()
        }
        binding.swipeRefreshLayoutContacts.setColorSchemeResources(R.color.color_accent)

        viewModel.contactsLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            binding.swipeRefreshLayoutContacts.isRefreshing = false
            Log.d(TAG, "Data submitted to adapter")
        }

        viewModel.isLoadingLiveData.observe(viewLifecycleOwner) {
            binding.progressBarContacts.isVisible = it
            binding.swipeRefreshLayoutContacts.isVisible = !it
        }
    }

    private fun createContactsList(adapter: ContactsListAdapter) {
        binding.listContacts.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(this.context, RecyclerView.VERTICAL)
        binding.listContacts.addItemDecoration(dividerItemDecoration)
    }

    companion object {
        private const val TAG = "ContactsFragment"
    }
}