package com.example.tenantmanagementsystemgroupa.data.model

import java.io.Serializable

// Serializable lets Safe Args carry one tenant between the fragments.
data class Tenant(
    val name: String,
    val phone: String,
    val rent: String
) : Serializable {
    fun summary(): String {
        return "Tenant: $name\nPhone: $phone\nRent: KSh $rent"
    }
}
