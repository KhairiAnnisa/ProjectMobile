package com.example.projectmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Patterns
import android.widget.Toast
import com.example.projectmobile.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    lateinit var binding : ActivityRegisterBinding
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.tvlogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnregister.setOnClickListener {
            val email = binding.edtemailregister.text.toString()
            val password = binding.edtpasswordregister.text.toString()

            //Validasi email
            if (email.isEmpty()){
                binding.edtemailregister.error = "Email Harus Diisi"
                binding.edtemailregister.requestFocus()
                return@setOnClickListener
            }

            //Validasi email tidak sesuai
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.edtemailregister.error = "Email Tidak Valid"
                binding.edtemailregister.requestFocus()
                return@setOnClickListener
            }

            //Validasi password
            if (password.isEmpty()){
                binding.edtpasswordregister.error = "Password Harus Diisi"
                binding.edtpasswordregister.requestFocus()
                return@setOnClickListener
            }

            //Validasi panjang password
            if (password.length < 6){
                binding.edtpasswordregister.error = "Password Minimal 6 Karakter"
                binding.edtpasswordregister.requestFocus()
                return@setOnClickListener
            }

            RegisterFirebase(email, password)
        }
    }
    private fun RegisterFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                } else{
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}