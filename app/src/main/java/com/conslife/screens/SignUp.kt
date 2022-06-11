package com.conslife.screens

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.conslife.R
import com.conslife.databinding.ActivitySignUpBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class SignUp : AppCompatActivity() {
    private lateinit var _binding: ActivitySignUpBinding
    private var auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        _binding.buttonSignup.setOnClickListener {
            signUp()
        }
        _binding.signIn.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun signUp() {
        val email = _binding.email.text.toString()
        val password = _binding.password.text.toString()

        if (checkEmail() && checkPassword()) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(baseContext, getString(R.string.toast_sign_up_success), Toast.LENGTH_SHORT).show()
                        login()
                    } else {
                        showMessage(getString(R.string.activity_sign_up_error_unknown))
                    }
                }.addOnFailureListener {
                    exceptionHandler(it)
                }
        }
    }

    private fun login() {
        val email = _binding.email.text.toString()
        val password = _binding.password.text.toString()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    showMessage(getString(R.string.login_failed))
                }
            }.addOnFailureListener {
                exceptionHandler(it)
            }
    }

    private fun exceptionHandler(exception: Exception) {
        val errorMessage = when (exception) {
            is FirebaseAuthWeakPasswordException -> getString(R.string.activity_sign_up_error_short_password)
            is FirebaseAuthInvalidCredentialsException -> getString(R.string.activity_sign_up_error_invalid_email)
            is FirebaseAuthUserCollisionException -> getString(R.string.activity_sign_up_error_email_taken)
            is FirebaseNetworkException -> getString(R.string.activity_sign_up_error_no_internet)
            else -> getString(R.string.activity_sign_up_error_unknown)
        }
        showMessage(errorMessage)
    }

    private fun checkEmail(): Boolean {
        val email = _binding.email.text.toString()
        if (email.isEmpty()) {
            showMessage(getString(R.string.activity_sign_up_error_empty_email))
            return false
        }
        return true
    }

    private fun checkPassword(): Boolean {
        val password = _binding.password.text.toString()
        val passwordConfirm = _binding.passwordConfirm.text.toString()
        if (password.isEmpty()) {
            showMessage(getString(R.string.activity_sign_up_error_empty_password))
            return false
        } else if (password.length < 6) {
            showMessage(getString(R.string.activity_sign_up_error_short_password))
            return false
        } else if (password != passwordConfirm) {
            showMessage(getString(R.string.activity_sign_up_error_password_mismatch))
            return false
        }
        return true
    }

    private fun showMessage(message: String) {
        Snackbar.make(_binding.root, message, Snackbar.LENGTH_LONG)
            .setBackgroundTint(getColor(R.color.conslife_magenta)).show()
    }
}