package com.example.kotlin_testdrivendevelopment.ui

object Validator {
    fun validateInput(amount: Int, description: String) =
        !(amount <= 0 || description.isEmpty())
}