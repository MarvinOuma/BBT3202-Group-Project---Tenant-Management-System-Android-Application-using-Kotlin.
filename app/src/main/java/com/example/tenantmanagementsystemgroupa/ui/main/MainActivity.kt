package com.example.tenantmanagementsystemgroupa.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tenantmanagementsystemgroupa.R
import com.example.tenantmanagementsystemgroupa.databinding.ActivityMainBinding
import com.example.tenantmanagementsystemgroupa.databinding.NavHeaderBinding
import com.example.tenantmanagementsystemgroupa.ui.auth.LoginActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logLifecycle("onCreate")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        // The host restores the current fragment and its arguments on rotation.
        val navHost = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHost.navController
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.dashboardFragment, R.id.addTenantFragment, R.id.viewTenantsFragment),
            binding.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        // NavigationView already inflated this header, so bind to the existing view.
        val headerBinding = NavHeaderBinding.bind(binding.navView.getHeaderView(0))
        headerBinding.headerEmailTextView.text =
            intent.getStringExtra(EMAIL_EXTRA) ?: getString(R.string.signed_in)

        // Back closes an open drawer before navigating away from the screen.
        val drawerBackCallback = object : OnBackPressedCallback(false) {
            override fun handleOnBackPressed() {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
        }
        onBackPressedDispatcher.addCallback(this, drawerBackCallback)
        binding.drawerLayout.addDrawerListener(object : DrawerLayout.SimpleDrawerListener() {
            override fun onDrawerOpened(drawerView: View) {
                drawerBackCallback.isEnabled = true
            }

            override fun onDrawerClosed(drawerView: View) {
                drawerBackCallback.isEnabled = false
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_logout) {
            // Clear the task so Back cannot return to the signed-in screens.
            val loginIntent = Intent(this, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(loginIntent)
            return true
        }
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        logLifecycle("onStart")
    }

    override fun onResume() {
        super.onResume()
        logLifecycle("onResume")
    }

    override fun onPause() {
        super.onPause()
        logLifecycle("onPause")
    }

    override fun onStop() {
        super.onStop()
        logLifecycle("onStop")
    }

    override fun onRestart() {
        super.onRestart()
        logLifecycle("onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        logLifecycle("onDestroy")
    }

    private fun logLifecycle(event: String) {
        Log.d("TENANT_LIFECYCLE", "MainActivity: $event")
    }

    companion object {
        const val EMAIL_EXTRA = "EMAIL"
    }
}
