package com.example.tenantmanagementsystemgroupa

// One in-memory list is shared by the tenant screens.
object TenantRepository {
    val tenants = mutableListOf<Tenant>()

    fun addTenant(tenant: Tenant) {
        tenants.add(tenant)
    }

    fun totalRent(): Double {
        return tenants.sumOf { it.rentAmount }
    }
}
