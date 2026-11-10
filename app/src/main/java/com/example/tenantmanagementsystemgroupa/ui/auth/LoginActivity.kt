package com.example.tenantmanagementsystemgroupa.ui.auth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tenantmanagementsystemgroupa.R
import com.example.tenantmanagementsystemgroupa.databinding.ActivityLoginBinding
import com.example.tenantmanagementsystemgroupa.ui.main.MainActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logLifecycle("onCreate")
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val registeredEmail = intent.getStringExtra(RegisterActivity.EMAIL_EXTRA)
        if (savedInstanceState == null && registeredEmail != null) {
            binding.emailEditText.setText(registeredEmail)
        }

        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, R.string.login_fields_required, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // This practical checks that both fields are filled; account storage comes later.
            startActivity(Intent(this, MainActivity::class.java).putExtra(MainActivity.EMAIL_EXTRA, email))
            finish()
        }

        binding.registerTextView.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.helpTextView.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.strathmore.edu")))
        }
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
        Log.d(LIFECYCLE_TAG, "LoginActivity: $event")
    }

    companion object {
        private const val LIFECYCLE_TAG = "TENANT_LIFECYCLE"
    }
}
