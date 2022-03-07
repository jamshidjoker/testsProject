package com.jamshidprograms.testp

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.jamshidprograms.testp.databinding.ActivityMainBinding
import com.jamshidprograms.testp.databinding.CustomAlertDialogBinding
import com.jamshidprograms.testp.managers.TestManager
import com.jamshidprograms.testp.models.QuestionData

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var questionTextView: TextView
    var doubleBackClick = false
    private val allVariationsViewGroup by lazy {
        ArrayList<ViewGroup>()
    }
    private var selectedVariationImageView: ImageView? = null
    private lateinit var nextBtn: Button
        private val questionsList by lazy { ArrayList<QuestionData>() }
    lateinit var testManager: TestManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getAllQuestions()
        testManager = TestManager(questionsList)
        loadView()
        loadDataToView()
    }

    private fun loadDataToView() {
        uncheck()
        questionTextView.text = testManager.getQuestion()
        (allVariationsViewGroup[0].getChildAt(1) as TextView).text = testManager.getVariantA()
        allVariationsViewGroup[0].setOnClickListener {
            selectVariation(it)
        }
        (allVariationsViewGroup[1].getChildAt(1) as TextView).text = testManager.getVariantB()
        allVariationsViewGroup[1].setOnClickListener {
            selectVariation(it)
        }
        (allVariationsViewGroup[2].getChildAt(1) as TextView).text = testManager.getVariantC()
        allVariationsViewGroup[2].setOnClickListener {
            selectVariation(it)
        }
        (allVariationsViewGroup[3].getChildAt(1) as TextView).text = testManager.getVariantD()
        allVariationsViewGroup[3].setOnClickListener {
            selectVariation(it)
        }
    }

    fun selectVariation(view: View) {
        val group = view as ViewGroup
        val selectedVariant = group.getChildAt(0) as ImageView
        uncheck()
        selectedVariant.setImageResource(R.drawable.ic_radio_button_checked_black_24dp)
        selectedVariationImageView = selectedVariant
    }

    private fun uncheck() {
        if (selectedVariationImageView != null) {
            selectedVariationImageView?.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp)
        }
    }

    private fun loadView() {
        questionTextView = findViewById(R.id.question)
        val viewGroup = findViewById<LinearLayout>(R.id.group)
        for (i in 0 until viewGroup.childCount) {
            if (viewGroup.getChildAt(i) is LinearLayout) {
                allVariationsViewGroup.add(viewGroup.getChildAt(i) as LinearLayout)
            }
        }
        nextBtn = findViewById(R.id.nextBtn)
        nextBtn.setOnClickListener {
            next()
        }
    }

    fun getAllQuestions() {
        questionsList.add(
            QuestionData(
                "Ular qanchalik ko’p bo’lsa, vazni shunchalik kamayib ketaveradi?", "G'ovaklar",
                "tosh", "kesak", "piyoz", "G'ovaklar"
            )
        )
        questionsList.add(
            QuestionData(
                "Uni chap qo’l bilan ushlasa bo’ladi, lekin o’ng qo’l bilan hech qachon ushlab bo’lmaydi?", "soch",
                "chap qo'l", "oyoq barmoqlar", "chap qo'l tirsagi", "chap qo'l tirsagi"
            )
        )
        questionsList.add(
            QuestionData(
                "Eng katta orol", "Grenlandiya",
                "Madagaskar", "Avstraliya", "Gavayi", "Grenlandiya"
            )
        )
        questionsList.add(
            QuestionData(
                "O’rdaklar suv ichishga qator bo’lib ketayotgan edi.\n O’rdaklardan biri oldinga qarab, 17 ta boshni ko’rdi.\n Ortiga qarasa — 42 ta panja kelayotgan ekan. \nJami nechta o’rdak suv ichishga ketmoqda?", "42",
                "38", "33", "39", "39"
            )
        )
        questionsList.add(
            QuestionData(
                "Stolning 4 ta burchagi bor. Shu burchaklardan birini kesib olib tashlasa, unda nechta burchak qoladi?", "3",
                "4", "5", "6", "5"
            )
        )
        questionsList.add(
            QuestionData(
                "Men suvman va suv yuzasida suzib yuripman. Men kimman?", "suv",
                "muz", "tosh", "gaz", "muz"
            )
        )
        questionsList.add(
            QuestionData(
                "Nimani 10 daqiqadan ortiq ushlab turishning iloji yo’q, garchi u o’ta yengil bo’lsa ham?", "suv",
                "gaz", "nafas", "mashina", "nafas"
            )
        )
        questionsList.add(
            QuestionData(
                "Yo’llari bor, yurib bo’lmaydi,\n" +
                        "yerlari bor — ekib bo’lmaydi,\n" +
                        "yam-yashil o’tloq bor — yulishni iloji yo’q,\n" +
                        "dengiz, daryo ko’llari bor, ammo suvi yo’q.\n" +
                        "Bu nima?", "kompyuter",
                "telefon", "stiv jobs", "xarita", "xarita"
            )
        )
        questionsList.add(
            QuestionData(
                "Choyni qaysi qo’l bilan aralashtirish kerak?", "o'ng",
                "chap", "ikkisi bilan baravariga", "qoshiqcha", "qoshiqcha"
            )
        )
        questionsList.add(
            QuestionData(
                "Yengil yoki og’ir bo’lishi mumkin. Lekin vaznga ega emas.\n" +
                        "Tez va asta bo’ladi, ammo yugurishga, uchishga, suzishga ham qodir emas.\n" +
                        "U nima?", "bill geyts",
                "musiqa", "klaviatura", "rus tili", "musiqa"
            )
        )

    }

    fun next() {
        if (selectedVariationImageView != null) {
            val viewGroup = selectedVariationImageView!!.parent as LinearLayout
            val textview = viewGroup.getChildAt(1) as TextView
            testManager.checkAnswer(textview.text.toString())
            if (testManager.hasNextQuestion()) {
                loadDataToView()
            } else {
                custom()
                saveData()
            }
        }
    }
    @SuppressLint("SetTextI18n")
    fun custom(){
        val binding: CustomAlertDialogBinding = CustomAlertDialogBinding.inflate(layoutInflater)

        val dialog = Dialog(this@MainActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(binding.root)
        dialog.setCancelable(true)
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        binding.btn1.setOnClickListener {
            dialog.cancel()
            Toast.makeText(this, "You clicked cancel", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@MainActivity, StartActivity::class.java))
        }
        binding.btn2.setOnClickListener {
            dialog.cancel()
            Toast.makeText(this, "You clicked OK", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@MainActivity, StartActivity::class.java))
        }
        binding.correct.text = testManager.correctAnswerCount.toString() + " ta to'g'ri javob"
        binding.wrong.text = testManager.wrongAnswerCount.toString() + " ta noto'g'ri javob"
        binding.percent.text = testManager.answerWithPercent().toString() + " % "
        binding.ikkisi.text = testManager.correctAnswerCount.toString() + " / " + "10"
        dialog.show()
    }
    private fun saveData(){
        val correctValue = testManager.correctAnswerCount.toString()
        val wrongValue = testManager.wrongAnswerCount.toString()
        val percentValue = testManager.answerWithPercent().toString() + " % "
        val ikkisi2 = testManager.correctAnswerCount.toString() + " / " + "10"
        val sharedPreferences:SharedPreferences = getSharedPreferences("dataStorage", Context.MODE_PRIVATE)
        val editor:SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("Correct1", correctValue)
        editor.putString("Wrong1", wrongValue)
        editor.putString("Percent1", percentValue)
        editor.putString("ikkisi1", ikkisi2)
        editor.apply()
    }


}