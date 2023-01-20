package com.example.projectmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_onboarding1.*

class Onboarding1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding1)

        btnGambarListener()
    }
    private fun btnGambarListener() {
        img_2.setOnClickListener {
            startActivity(Intent(this, Onboarding2Activity::class.java))
        }
    }
}