package com.jamshidprograms.testp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton

class StartActivity : AppCompatActivity() {
    lateinit var button:AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        button = findViewById(R.id.button)
        button.setOnClickListener {
            startActivity(Intent(this@StartActivity, MainActivity::class.java))
        }
    }
}