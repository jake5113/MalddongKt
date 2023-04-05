package com.jake5113.malddongkt.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jake5113.malddongkt.R
import com.jake5113.malddongkt.databinding.ActivityLoginBinding
import com.jake5113.malddongkt.main.MainActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNoLogin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}