Tenant Management System

A Kotlin Android application for landlords to record tenants, monitor total rent, and call tenants directly from the app.

Course: BBT3202 — Mobile Application Development
Institution: Strathmore University, School of Computing and Engineering Sciences

Download

The signed Android APK will be available on the GitHub Releases page after the v1.0 release is published.

Features

* Register and log in.
* View the number of tenants and total rent on the Dashboard.
* Add tenants with their names, phone numbers, and rent amounts.
* View all tenants, with records stored locally using Room.
* Open the phone dialer to call a tenant.
* Navigate using the app’s navigation drawer and options menu.
* Keep tenant records after closing and reopening the app through Room database storage.

Architecture

The application is organised into two main layers:

* ui/ — User interface: Activities, Fragments, and TenantViewModel. Fragments display data, collect user input, and navigate between screens.
* data/ — Data layer: Tenant, TenantDao, TenantDatabase, and TenantRepository. Room stores tenant records on the device.

The data flows through the application as follows:

Screens (Fragments) → TenantViewModel (LiveData) → TenantRepository → Room Database

The ViewModel exposes observable tenant information through LiveData, while the repository handles database operations. Room updates the observable data whenever tenant records change.

Team

| Stage | Member | Admission No. | Pull request |
|---|---|---|---|
| 1 Lead / Architecture | Marvin Ouma | 190164 | [PR #1](https://github.com/MarvinOuma/BBT3202-Group-Project---Tenant-Management-System-Android-Application-using-Kotlin./pull/1) |
| 2 ViewModel + LiveData | Irene Gitau Nyambura | 147423 | [PR #2](https://github.com/MarvinOuma/BBT3202-Group-Project---Tenant-Management-System-Android-Application-using-Kotlin./pull/2) |
| 3 Room Database | Chanel Nasokho | 175716 | [PR #4](https://github.com/MarvinOuma/BBT3202-Group-Project---Tenant-Management-System-Android-Application-using-Kotlin./pull/4) |
| 4 Screens | Emmanuel Mutinda | 177292 | [PR #3](https://github.com/MarvinOuma/BBT3202-Group-Project---Tenant-Management-System-Android-Application-using-Kotlin./pull/3) |
| 5 Data Integration | Caleb Kimutai | 173572 | [PR #5](https://github.com/MarvinOuma/BBT3202-Group-Project---Tenant-Management-System-Android-Application-using-Kotlin./pull/5) |
| 6 Release | Paul Mukua | 193425 | [PR #6](https://github.com/MarvinOuma/BBT3202-Group-Project---Tenant-Management-System-Android-Application-using-Kotlin./pull/6) |