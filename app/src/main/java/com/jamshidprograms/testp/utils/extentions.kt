package com.jamshidprograms.testp.utils

import android.widget.Toast
import com.jamshidprograms.testp.MainActivity

fun MainActivity.toast(message:String){
    Toast.makeText(this,message,Toast.LENGTH_LONG).show()
}