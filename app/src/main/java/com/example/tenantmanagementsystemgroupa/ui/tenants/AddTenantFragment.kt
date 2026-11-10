package com.example.tenantmanagementsystemgroupa.ui.tenants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tenantmanagementsystemgroupa.R
import com.example.tenantmanagementsystemgroupa.data.model.Tenant
import com.example.tenantmanagementsystemgroupa.data.repository.TenantRepository
import com.example.tenantmanagementsystemgroupa.databinding.FragmentAddTenantBinding

class AddTenantFragment : Fragment() {
    private var _binding: FragmentAddTenantBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTenantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveButton.setOnClickListener {
            val name = binding.tenantNameEditText.text.toString().trim()
            val phone = binding.phoneEditText.text.toString().trim()
            val rent = binding.rentEditText.text.toString().trim()
            var hasError = false

            if (name.isEmpty()) {
                binding.tenantNameEditText.error = getString(R.string.tenant_name_required)
                hasError = true
            }
            if (phone.isEmpty()) {
                binding.phoneEditText.error = getString(R.string.phone_required)
                hasError = true
            }
            val amount = rent.toDoubleOrNull()
            if (amount == null || !amount.isFinite() || amount < 0.0) {
                binding.rentEditText.error = getString(R.string.rent_invalid)
                hasError = true
            }
            if (hasError) return@setOnClickListener

            val tenant = Tenant(name, phone, rent)
            TenantRepository.addTenant(tenant)
            // This action and its Tenant argument are generated from nav_graph.xml.
            val action = AddTenantFragmentDirections
                .actionAddTenantFragmentToViewTenantsFragment(tenant)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
