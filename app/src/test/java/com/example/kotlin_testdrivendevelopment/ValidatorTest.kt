package com.example.kotlin_testdrivendevelopment

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidatorTest{

    @Test
    fun whenInputisValid(){
         val amount =100
        val description = "Some random text"
        val result = Validator.validateinput(amount,description)
        assertThat(result).isEqualTo(true)

    }
    @Test
    fun whenInputisInValid(){
        val amount =0
        val description = ""
        val result = Validator.validateinput(amount,description)
        assertThat(result).isEqualTo(false)

    }

}