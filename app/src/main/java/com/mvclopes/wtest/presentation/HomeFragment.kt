package com.mvclopes.wtest.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mvclopes.wtest.R
import com.mvclopes.wtest.databinding.FragmentHomeBinding
import com.mvclopes.wtest.domain.model.PostalCode
import com.mvclopes.wtest.presentation.adapter.PostalCodeAdapter

class HomeFragment : Fragment() {

    private val binding: FragmentHomeBinding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val adapter: PostalCodeAdapter by lazy { PostalCodeAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.postalCodeRecycler.adapter = adapter
        adapter.submitList(mockPostalCodes())

        return binding.root
    }

    private fun mockPostalCodes() = listOf(
        PostalCode(
            postalCodeNumber = "3750-011",
            postalDesignation = "AGADÃO"
        ),
        PostalCode(
            postalCodeNumber = "3750-012",
            postalDesignation = "ALGARVE"
        ),
        PostalCode(
            postalCodeNumber = "3750-013",
            postalDesignation = "LISBOA"
        ),
        PostalCode(
            postalCodeNumber = "3750-014",
            postalDesignation = "PORTO"
        ),
        PostalCode(
            postalCodeNumber = "3750-015",
            postalDesignation = "SAGRES"
        ),
        PostalCode(
            postalCodeNumber = "3750-011",
            postalDesignation = "AGADÃO"
        ),
        PostalCode(
            postalCodeNumber = "3750-012",
            postalDesignation = "ALGARVE"
        ),
        PostalCode(
            postalCodeNumber = "3750-013",
            postalDesignation = "LISBOA"
        ),
        PostalCode(
            postalCodeNumber = "3750-014",
            postalDesignation = "PORTO"
        ),
        PostalCode(
            postalCodeNumber = "3750-015",
            postalDesignation = "SAGRES"
        ),
        PostalCode(
            postalCodeNumber = "3750-011",
            postalDesignation = "AGADÃO"
        ),
        PostalCode(
            postalCodeNumber = "3750-012",
            postalDesignation = "ALGARVE"
        ),
        PostalCode(
            postalCodeNumber = "3750-013",
            postalDesignation = "LISBOA"
        ),
        PostalCode(
            postalCodeNumber = "3750-014",
            postalDesignation = "PORTO"
        ),
        PostalCode(
            postalCodeNumber = "3750-015",
            postalDesignation = "SAGRES"
        ),
        PostalCode(
            postalCodeNumber = "3750-011",
            postalDesignation = "AGADÃO"
        ),
        PostalCode(
            postalCodeNumber = "3750-012",
            postalDesignation = "ALGARVE"
        ),
        PostalCode(
            postalCodeNumber = "3750-013",
            postalDesignation = "LISBOA"
        ),
        PostalCode(
            postalCodeNumber = "3750-014",
            postalDesignation = "PORTO"
        ),
        PostalCode(
            postalCodeNumber = "3750-015",
            postalDesignation = "SAGRES"
        )
    )
}