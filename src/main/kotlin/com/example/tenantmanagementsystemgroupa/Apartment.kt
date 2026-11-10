package com.example.tenantmanagementsystemgroupa

class Apartment(val apartmentNumber: Int) {
    // An apartment has tenant objects, so it keeps them together in this list.
    val tenants: MutableList<Tenant> = mutableListOf()

    fun addTenant(tenant: Tenant) {
        tenants.add(tenant)
    }

    fun showTenants() {
        println("Apartment: $apartmentNumber")
        for (tenant in tenants) {
            println("Tenant: ${tenant.name}")
            println("Rent: ${tenant.rentAmount}")
            println("Rent paid: ${tenant.isPaid}")
        }
    }
}
