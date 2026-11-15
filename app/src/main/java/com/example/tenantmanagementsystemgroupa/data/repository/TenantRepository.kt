package com.example.tenantmanagementsystemgroupa.data.repository

import androidx.lifecycle.LiveData
import com.example.tenantmanagementsystemgroupa.data.local.TenantDao
import com.example.tenantmanagementsystemgroupa.data.model.Tenant

class TenantRepository(private val tenantDao: TenantDao) {
    val tenants: LiveData<List<Tenant>> = tenantDao.getAllTenants()

    suspend fun addTenant(tenant: Tenant) {
        tenantDao.insertTenant(tenant)
    }
}
