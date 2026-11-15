package com.example.tenantmanagementsystemgroupa.ui.tenants

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.tenantmanagementsystemgroupa.databinding.FragmentViewTenantsBinding
import com.example.tenantmanagementsystemgroupa.ui.viewmodel.TenantViewModel

class ViewTenantsFragment : Fragment() {
    private var _binding: FragmentViewTenantsBinding? = null
    private val binding get() = _binding!!
    private val args: ViewTenantsFragmentArgs by navArgs()
    private val viewModel: TenantViewModel by activityViewModels()

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

        viewModel.allTenantsText.observe(viewLifecycleOwner) { text ->
            binding.allTenantsTextView.text = text
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
