package com.example.projectmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_onboarding1.*
import kotlinx.android.synthetic.main.activity_onboarding2.*

class Onboarding2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding2)

        btnGambarListener()
    }
    private fun btnGambarListener() {
        img_3.setOnClickListener {
            startActivity(Intent(this, Onboarding3Activity::class.java))
        }
    }
}