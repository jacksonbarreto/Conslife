package com.conslife.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.conslife.R
import com.conslife.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.*

class Login : AppCompatActivity() {
    private lateinit var _binding: ActivityLoginBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private val auth = FirebaseAuth.getInstance()

    private companion object {
        const val RC_SIGN_IN = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        val btnLogin = _binding.buttonLogin
        btnLogin.setOnClickListener {
            login()
        }
        _binding.textViewCreateAccountText2.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
            finish()
        }
        _binding.buttonGoogle.setOnClickListener {
            signInWithGoogle()
        }
        setupGoogleSignInClient()
    }

    override fun onStart() {
        super.onStart()
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            goToMainActivity()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                showMessage(getString(R.string.google_sign_failed))
            }
        }
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential)
            .addOnSuccessListener { task ->
                if (task.additionalUserInfo!!.isNewUser) {
                    Toast.makeText(
                        baseContext,
                        getString(R.string.toast_sign_up_success),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                goToMainActivity()
            }.addOnFailureListener { exceptionHandler(it) }
    }

    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun login() {
        val email = _binding.editTextEmail.text.toString()
        val password = _binding.editTextPassword.text.toString()

        if (checkEmail() && checkPassword()) {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    goToMainActivity()
                } else {
                    showMessage(getString(R.string.login_failed))
                }
            }.addOnFailureListener {
                exceptionHandler(it)
            }
        }
    }


    private fun checkEmail(): Boolean {
        val email = _binding.editTextEmail.text.toString()
        if (email.isEmpty()) {
            showMessage(getString(R.string.activity_sign_up_error_empty_email))
            return false
        }
        return true
    }

    private fun checkPassword(): Boolean {
        val password = _binding.editTextPassword.text.toString()
        if (password.isEmpty()) {
            showMessage(getString(R.string.activity_sign_up_error_empty_password))
            return false
        } else if (password.length < 6) {
            showMessage(getString(R.string.activity_sign_up_error_short_password))
            return false
        }
        return true
    }

    private fun showMessage(message: String) {
        Snackbar.make(_binding.root, message, Snackbar.LENGTH_LONG)
            .setBackgroundTint(getColor(R.color.conslife_magenta)).show()
    }

    private fun exceptionHandler(exception: Exception) {
        val errorMessage = when (exception) {
            is FirebaseAuthWeakPasswordException -> getString(R.string.activity_sign_up_error_short_password)
            is FirebaseAuthInvalidCredentialsException -> getString(R.string.activity_sign_up_error_invalid_email)
            is FirebaseAuthUserCollisionException -> getString(R.string.activity_sign_up_error_email_taken)
            is FirebaseNetworkException -> getString(R.string.activity_sign_up_error_no_internet)
            else -> getString(R.string.login_failed)
        }
        showMessage(errorMessage)
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun setupGoogleSignInClient() {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)
    }


}