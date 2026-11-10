package com.example.tenantmanagementsystemgroupa.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tenantmanagementsystemgroupa.R
import com.example.tenantmanagementsystemgroupa.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logLifecycle("onCreate")
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerButton.setOnClickListener {
            val fullName = binding.fullNameEditText.text.toString().trim()
            val email = binding.registerEmailEditText.text.toString().trim()
            val password = binding.registerPasswordEditText.text.toString()
            if (fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, R.string.all_fields_required, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, R.string.registration_complete, Toast.LENGTH_SHORT).show()
            val loginIntent = Intent(this, LoginActivity::class.java)
                .putExtra(EMAIL_EXTRA, email)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(loginIntent)
        }

        binding.loginLinkTextView.setOnClickListener {
            finish()
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
        Log.d(LIFECYCLE_TAG, "RegisterActivity: $event")
    }

    companion object {
        const val EMAIL_EXTRA = "EMAIL"
        private const val LIFECYCLE_TAG = "TENANT_LIFECYCLE"
    }
}
