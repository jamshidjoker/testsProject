package com.jamshidprograms.testp

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jamshidprograms.testp.databinding.ActivityStartBinding
import com.jamshidprograms.testp.databinding.CustomAlertDialogBinding
import com.jamshidprograms.testp.managers.TestManager

class StartActivity : AppCompatActivity() {
    lateinit var binding: ActivityStartBinding
    lateinit var testManager: TestManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            startActivity(Intent(this@StartActivity, MainActivity::class.java))
        }


        binding.songgi.setOnClickListener {
            readData()
        }
    }
    fun readData(){
        val sharedPreferences: SharedPreferences = getSharedPreferences("dataStorage", Context.MODE_PRIVATE)
        val correctText = sharedPreferences.getString("Correct1", "Topilmadi")
        val wrongText = sharedPreferences.getString("Wrong1", "Topilmadi")
        val ikkisiText = sharedPreferences.getString("ikkisi1", "Topilmadi")
        val percentText = sharedPreferences.getString("Percent1", "Topilmadi")
        val builder: AlertDialog.Builder = AlertDialog.Builder(this@StartActivity)
        builder.setCancelable(false)
        builder.setIcon(android.R.drawable.presence_away)
        builder.setTitle("So'nggi Natija")
        builder.setMessage("To'g'ri javoblar soni : $correctText\n" +
                "Noto'g'ri javoblar soni : $wrongText\n" +
                "$percentText % to'g'ri topildi" +
                "   $ikkisiText    "
        )
        builder.setPositiveButton("Ok", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                Toast.makeText(this@StartActivity, "OKay", Toast.LENGTH_SHORT).show()
            }

        })
        builder.setNegativeButton("Cancel", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                Toast.makeText(this@StartActivity, "Cancelled", Toast.LENGTH_SHORT).show()
            }
        })
        builder.show()
    }
}