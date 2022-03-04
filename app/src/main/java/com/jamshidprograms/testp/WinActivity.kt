package com.jamshidprograms.testp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class WinActivity : AppCompatActivity() {
    lateinit var correct:TextView
    lateinit var wrong:TextView
    lateinit var percent:TextView
    lateinit var dvoe:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_win)
        correct = findViewById(R.id.correct)
        wrong = findViewById(R.id.wrong)
        percent = findViewById(R.id.percent)
        dvoe = findViewById(R.id.ikkisi)
        correct.text = intent.getStringExtra("CORRECT") + "ta to'g'ri javob berdingiz!"
        wrong.text = intent.getStringExtra("WRONG") + "ta xato javob berdingiz"
        percent.text = intent.getStringExtra("PERCENT") + ""
        dvoe.text = intent.getStringExtra("CORRECT").toString() + " / " + "10"
    }
}