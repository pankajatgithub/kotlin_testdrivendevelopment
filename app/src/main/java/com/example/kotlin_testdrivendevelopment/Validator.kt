package com.example.kotlin_testdrivendevelopment

object Validator {
    fun validateinput(amount : Int,description : String): Boolean {
  return !(amount <= 0 || description.isEmpty())
    }
}