package com.conslife.widget

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import com.conslife.databinding.ResHeaderMainBinding
import com.conslife.screens.Login
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class HeaderMain(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    private val binding: ResHeaderMainBinding =
        ResHeaderMainBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.logOut.setOnClickListener {
            onLogOutClicked()
        }
    }

    private fun onLogOutClicked() {
        FirebaseAuth.getInstance().signOut()
        goToLogin()
    }

    private fun goToLogin() {
        val intent = Intent(context, Login::class.java)
        startActivity(context, intent, null)
        (context as AppCompatActivity).finish()
    }
}