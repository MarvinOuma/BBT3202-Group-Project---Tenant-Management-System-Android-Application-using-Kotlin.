package com.example.tenantmanagementsystemgroupa

import java.io.Serializable

/** Holds one tenant's details and rent status. */
class Tenant(
    val name: String,
    val apartmentNumber: Int,
    val phone: String,
    initialRentAmount: Double
) : Serializable {
    // The setter checks the starting amount as well as later changes.
    var rentAmount: Double = 0.0
        get() {
            // Kotlin runs this getter whenever code reads rentAmount.
            println("Rent amount accessed.")
            return field
        }
        set(value) {
            // A setter runs when code assigns a new rent amount.
            if (value >= 0.0) {
                field = value
            } else {
                // Checking before storing stops invalid rent data from being kept.
                println("Rent amount cannot be negative.")
            }
        }

    var isPaid: Boolean = false
        private set

    // Restore the saved payment state after Android recreates the Activity.
    fun restorePaymentStatus(paid: Boolean) {
        isPaid = paid
    }

    // The three-argument constructor keeps the Lab 3 class easy to create.
    constructor(name: String, apartmentNumber: Int, initialRentAmount: Double) :
        this(name, apartmentNumber, "", initialRentAmount)

    init {
        rentAmount = initialRentAmount
    }

    fun payRent() {
        isPaid = true
        println("Rent paid successfully by $name")
    }

    fun summary(): String {
        return "Tenant: $name\nApartment: $apartmentNumber\nPhone: $phone\n" +
            "Rent: KSh $rentAmount\nRent paid: $isPaid"
    }
}
