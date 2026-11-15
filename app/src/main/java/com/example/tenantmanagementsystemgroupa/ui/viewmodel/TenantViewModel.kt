package com.example.tenantmanagementsystemgroupa.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.tenantmanagementsystemgroupa.data.model.Tenant
import com.example.tenantmanagementsystemgroupa.data.repository.TenantRepository
import java.util.Locale

class TenantViewModel : ViewModel() {
    private val _tenants = MutableLiveData(TenantRepository.tenants.toList())
    val tenants: LiveData<List<Tenant>> = _tenants

    val tenantCountText: LiveData<String> = tenants.map { currentTenants ->
        "Tenants\n${currentTenants.size}"
    }

    val totalRentText: LiveData<String> = tenants.map { currentTenants ->
        val totalRent = currentTenants.sumOf { it.rent.toDoubleOrNull() ?: 0.0 }
        "Total rent\nKSh ${String.format(Locale.US, "%,.0f", totalRent)}"
    }

    val allTenantsText: LiveData<String> = tenants.map { currentTenants ->
        if (currentTenants.isEmpty()) {
            "No tenants yet. Add one from the Dashboard."
        } else {
            currentTenants.joinToString("\n\n") { it.summary() }
        }
    }

    init {
        Log.d(TAG, "TenantViewModel created")
    }

    /** Adds and publishes a tenant when all fields meet the existing form's validation rules. */
    fun addTenant(name: String, phone: String, rent: String): Tenant? {
        val trimmedName = name.trim()
        val trimmedPhone = phone.trim()
        val trimmedRent = rent.trim()
        val rentAmount = trimmedRent.toDoubleOrNull()

        if (trimmedName.isEmpty() || trimmedPhone.isEmpty() ||
            rentAmount == null || !rentAmount.isFinite() || rentAmount < 0.0
        ) {
            return null
        }

        val tenant = Tenant(trimmedName, trimmedPhone, trimmedRent)
        TenantRepository.addTenant(tenant)
        _tenants.value = TenantRepository.tenants.toList()
        return tenant
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "TenantViewModel cleared")
    }

    private companion object {
        const val TAG = "TENANT_LIFECYCLE"
    }
}
