package com.mlpozdeev.contactsapp.presentation.fragments.profile.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mlpozdeev.contactsapp.ContactsApp
import com.mlpozdeev.contactsapp.databinding.FragmentProfileBinding
import com.mlpozdeev.contactsapp.presentation.fragments.profile.viewmodel.ProfileViewModel

class ProfileFragment : Fragment() {

    private lateinit var viewModel: ProfileViewModel

    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!
    private val args: ProfileFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val contactId = args.contactId
        val loadContactUseCase = (requireActivity().application as ContactsApp).appComponent
            .getLoadContactUseCase()

        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return ProfileViewModel(
                    contactId,
                    loadContactUseCase
                ) as T
            }
        }).get(ProfileViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val appCompatActivity = (requireActivity() as AppCompatActivity)
        appCompatActivity.setSupportActionBar(binding.toolbarProfile)
        appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        appCompatActivity.supportActionBar?.setDisplayShowHomeEnabled(true)
        appCompatActivity.supportActionBar?.title = ""
        binding.toolbarProfile.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.profileLiveData.observe(viewLifecycleOwner) {
            binding.textViewProfileName.text = it.name
            binding.textViewProfilePhoneNumber.text = it.phoneNumber
            binding.textViewProfileTemperament.text = it.temperament
            binding.textViewProfileEducationPeriod.text = it.educationPeriod
            binding.textViewProfileBiography.text = it.biography
        }

        binding.textViewProfilePhoneNumber.setOnClickListener {
            openCall((it as TextView).text.toString())
        }
    }

    private fun openCall(phone: String) {
        val calIntent = Intent(Intent.ACTION_DIAL)
        calIntent.data = Uri.parse("tel:$phone")
        startActivity(calIntent)
    }
}