package com.jamshidprograms.testp.managers

import com.jamshidprograms.testp.models.QuestionData

class TestManager(var questionsList: ArrayList<QuestionData>) {
    var currentQuestionPosition: Int = 0
    var correctAnswerCount = 0
    var wrongAnswerCount = 0
    var percent = 0
    fun getQuestion(): String {
        return questionsList[currentQuestionPosition].question
    }

    fun getAnswer(): String {
        return questionsList[currentQuestionPosition].answer
    }

    fun getVariantA(): String {
        return questionsList[currentQuestionPosition].variantA
    }

    fun getVariantB(): String {
        return questionsList[currentQuestionPosition].variantB
    }

    fun getVariantC(): String {
        return questionsList[currentQuestionPosition].variantC
    }

    fun getVariantD(): String {
        return questionsList[currentQuestionPosition].variantD
    }

    fun getQuestionSize() = questionsList.size

    fun hasNextQuestion(): Boolean {
        if (currentQuestionPosition < getQuestionSize() - 1) {
            currentQuestionPosition++
            return true
        }
        return false
    }

    fun checkAnswer(answer: String) {
        if (answer == getAnswer()) {
            correctAnswerCount++
        } else {
            wrongAnswerCount++
        }
    }

    fun answerWithPercent() = correctAnswerCount * 100 / getQuestionSize()

}