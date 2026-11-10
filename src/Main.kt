package com.example.tenantmanagementsystemgroupa

fun main() {
    val apartment = Apartment(101)

    // A constructor gives each object its own tenant information.
    val firstTenant = Tenant("John", 101, "0712345678", 15000.0)
    val secondTenant = Tenant("Mary", 101, "0723456789", 18000.0)

    // Two objects from one class can hold different values and payment statuses.
    apartment.addTenant(firstTenant)
    apartment.addTenant(secondTenant)
    firstTenant.payRent()
    println("${firstTenant.name} rent paid: ${firstTenant.isPaid}")
    println("${secondTenant.name} rent paid: ${secondTenant.isPaid}")

    // The constructor stores valid rent; the setter rejects a later negative amount.
    val validationTenant = Tenant("Amina", 101, 12000.0)
    validationTenant.rentAmount = -500.0
    println("Rent after the rejected change: ${validationTenant.rentAmount}")

    // Apartment keeps a list of tenants and displays each tenant's current details.
    apartment.showTenants()
}
