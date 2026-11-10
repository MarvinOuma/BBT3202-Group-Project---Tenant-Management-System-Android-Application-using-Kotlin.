package com.example.tenantmanagementsystemgroupa.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tenantmanagementsystemgroupa.R
import com.example.tenantmanagementsystemgroupa.data.repository.TenantRepository
import com.example.tenantmanagementsystemgroupa.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logLifecycle("onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        logLifecycle("onCreateView")
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logLifecycle("onViewCreated")

        val tenantCount = TenantRepository.tenants.size
        val totalRent = TenantRepository.totalRent()
        binding.tenantCountTextView.text = getString(R.string.tenant_count, tenantCount)
        binding.totalRentTextView.text = getString(R.string.total_rent, totalRent)

        binding.addTenantButton.setOnClickListener {
            val action = DashboardFragmentDirections.actionDashboardFragmentToAddTenantFragment()
            findNavController().navigate(action)
        }
        binding.viewTenantsButton.setOnClickListener {
            val action = DashboardFragmentDirections.actionDashboardFragmentToViewTenantsFragment()
            findNavController().navigate(action)
        }
    }

    override fun onStart() {
        super.onStart()
        logLifecycle("onStart")
    }

    override fun onResume() {
        super.onResume()
        logLifecycle("onResume")
    }

    override fun onPause() {
        super.onPause()
        logLifecycle("onPause")
    }

    override fun onStop() {
        super.onStop()
        logLifecycle("onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        logLifecycle("onDestroyView")
        // The fragment can outlive its view; release the old binding.
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        logLifecycle("onDestroy")
    }

    private fun logLifecycle(event: String) {
        Log.d("TENANT_LIFECYCLE", "  DashboardFragment: $event")
    }
}
