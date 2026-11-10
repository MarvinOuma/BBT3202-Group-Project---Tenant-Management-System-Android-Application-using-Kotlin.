package com.example.tenantmanagementsystemgroupa.ui.tenants

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.tenantmanagementsystemgroupa.R
import com.example.tenantmanagementsystemgroupa.data.repository.TenantRepository
import com.example.tenantmanagementsystemgroupa.databinding.FragmentViewTenantsBinding

class ViewTenantsFragment : Fragment() {
    private var _binding: FragmentViewTenantsBinding? = null
    private val binding get() = _binding!!
    private val args: ViewTenantsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewTenantsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Fragment arguments restore the just-added tenant after rotation.
        val newTenant = args.newTenant
        binding.tenant = newTenant
        binding.newTenantGroup.visibility = if (newTenant == null) View.GONE else View.VISIBLE

        binding.callButton.setOnClickListener {
            if (newTenant != null) {
                // Fill the dialer; the user decides whether to place the call.
                startActivity(Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", newTenant.phone, null)))
            }
        }

        val tenants = TenantRepository.tenants
        binding.allTenantsTextView.text = if (tenants.isEmpty()) {
            getString(R.string.no_tenants)
        } else {
            tenants.joinToString("\n\n") { it.summary() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
