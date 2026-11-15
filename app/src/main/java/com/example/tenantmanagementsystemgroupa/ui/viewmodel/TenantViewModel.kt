package com.example.tenantmanagementsystemgroupa.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.tenantmanagementsystemgroupa.data.local.TenantDatabase
import com.example.tenantmanagementsystemgroupa.data.model.Tenant
import com.example.tenantmanagementsystemgroupa.data.repository.TenantRepository
import kotlinx.coroutines.launch
import java.util.Locale

class TenantViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = TenantRepository(
        TenantDatabase.getDatabase(application).tenantDao()
    )
    val tenants: LiveData<List<Tenant>> = repository.tenants

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
        viewModelScope.launch {
            repository.addTenant(tenant)
        }
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
