package com.mlpozdeev.contactsapp.presentation.fragments.contacts.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.mlpozdeev.contactsapp.R
import com.mlpozdeev.contactsapp.databinding.FragmentContactsBinding
import com.mlpozdeev.contactsapp.presentation.fragments.contacts.model.ContactItem

class ContactsFragment : Fragment() {

    private var _binding: FragmentContactsBinding? = null

    private val binding get() = _binding!!

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

        //test
        val list = listOf(
            ContactItem(
                id = 0,
                name = "Carol Rodriguez",
                height = "195.3",
                phoneNumber = "+7 (828) 461-2624"
            ),
            ContactItem(
                id = 0,
                name = "Carol Rodriguez",
                height = "195.3",
                phoneNumber = "+7 (828) 461-2624"
            ),
            ContactItem(
                id = 0,
                name = "Carol Rodriguez",
                height = "195.3",
                phoneNumber = "+7 (828) 461-2624"
            ),
            ContactItem(
                id = 0,
                name = "Carol Rodriguez",
                height = "195.3",
                phoneNumber = "+7 (828) 461-2624"
            ),
            ContactItem(
                id = 0,
                name = "Carol Rodriguez",
                height = "195.3",
                phoneNumber = "+7 (828) 461-2624"
            ),
            ContactItem(
                id = 0,
                name = "Carol Rodriguez",
                height = "195.3",
                phoneNumber = "+7 (828) 461-2624"
            ),
            ContactItem(
                id = 0,
                name = "Carol Rodriguez",
                height = "195.3",
                phoneNumber = "+7 (828) 461-2624"
            ),
            ContactItem(
                id = 0,
                name = "Carol Rodriguez",
                height = "195.3",
                phoneNumber = "+7 (828) 461-2624"
            ),
            ContactItem(
                id = 0,
                name = "Carol Rodriguez",
                height = "195.3",
                phoneNumber = "+7 (828) 461-2624"
            ),
            ContactItem(
                id = 0,
                name = "Carol Rodriguez",
                height = "195.3",
                phoneNumber = "+7 (828) 461-2624"
            )
        )

        adapter.submitList(list)
    }

    private fun createContactsList(adapter: ContactsListAdapter) {
        binding.listContacts.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(this.context, RecyclerView.VERTICAL)
        binding.listContacts.addItemDecoration(dividerItemDecoration)
    }
}