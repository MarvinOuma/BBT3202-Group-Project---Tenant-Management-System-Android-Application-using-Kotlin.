package com.example.tenantmanagementsystemgroupa.data.repository

import com.example.tenantmanagementsystemgroupa.data.model.Tenant

// The fragments share one list, which lasts only while the app process lives.
object TenantRepository {
    val tenants = mutableListOf<Tenant>()

    fun addTenant(tenant: Tenant) {
        tenants.add(tenant)
    }

    fun totalRent(): Double {
        return tenants.sumOf { it.rent.toDoubleOrNull() ?: 0.0 }
    }
}
