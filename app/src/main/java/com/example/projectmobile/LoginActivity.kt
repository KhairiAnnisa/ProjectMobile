package com.example.projectmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.projectmobile.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

//    lateinit var binding : ActivityLoginBinding
    lateinit var auth : FirebaseAuth
    lateinit var button: Button
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var isiEmail: String
    lateinit var isiPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        title = "KotlinApp"
        button = findViewById(R.id.btnmasuk)
        email = findViewById(R.id.edtemaillogin)
        password = findViewById(R.id.edtpasswordlogin)

        button.setOnClickListener(){
            isiEmail = email.text.toString()
            isiPassword = password.text.toString()
            LoginFirebase(isiEmail, isiPassword)
            Log.i("data : ", isiEmail + isiPassword)

            //Validasi email
            if (isiEmail.isEmpty()){
                email.error = "Email Harus Diisi"
                email.requestFocus()
                return@setOnClickListener
            }

            //Jika email salah
            if (!Patterns.EMAIL_ADDRESS.matcher(isiEmail).matches()) {
                email.error = "Email Tidak Valid"
                password.requestFocus()
                return@setOnClickListener
            }

            //Validasi Password
            if (isiPassword.isEmpty()) {
                password.error = "Password Harus Diisi"
                password.requestFocus()
                return@setOnClickListener
            }
        }

    }

    private fun LoginFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if (it.isSuccessful) {
                    Toast.makeText(this, "Selamat Datang $email", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun keRegister(view: View?){
        val i = Intent(applicationContext, RegisterActivity::class.java)
        i.putExtra("Value1", "Sedang berada di halaman login")
        startActivity(i)
    }

    }
