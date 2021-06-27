package com.mlpozdeev.contactsapp.presentation.fragments.contacts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
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
        setContactsList(adapter)
        setSwipeRefreshLayout()
        setErrorHandling()
        setLoadStateHandling()
        setSearchHandling(adapter)
    }

    private fun setContactsList(adapter: ContactsListAdapter) {
        binding.listContacts.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(this.context, RecyclerView.VERTICAL)
        binding.listContacts.addItemDecoration(dividerItemDecoration)

        viewModel.contactsLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun setSwipeRefreshLayout() {
        binding.swipeRefreshLayoutContacts.setOnRefreshListener {
            viewModel.refreshData()
        }
        binding.swipeRefreshLayoutContacts.setColorSchemeResources(R.color.color_accent)
    }

    private fun setErrorHandling() {
        val snackBar = Snackbar.make(requireView(), "", Snackbar.LENGTH_INDEFINITE)
        viewModel.errorMessageLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                snackBar.setText(it)
                snackBar.show()
            } else {
                snackBar.dismiss()
            }
        }
    }

    private fun setLoadStateHandling() {
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner) {
            binding.progressBarContacts.isVisible = it
            binding.swipeRefreshLayoutContacts.isVisible = !it
        }

        viewModel.isForceLoadingLiveData.observe(viewLifecycleOwner) {
            binding.swipeRefreshLayoutContacts.isRefreshing = it
        }
    }

    private fun setSearchHandling(adapter: ContactsListAdapter) {
        binding.searchViewContacts.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter(newText ?: "")
                return false
            }
        })
    }

    companion object {
        private const val TAG = "ContactsFragment"
    }
}