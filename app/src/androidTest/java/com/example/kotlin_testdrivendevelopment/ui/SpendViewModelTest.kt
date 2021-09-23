package com.example.kotlin_testdrivendevelopment.ui

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.kotlin_testdrivendevelopment.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import net.simplifiedcoding.spendtracker.data.SpendsDatabase
import net.simplifiedcoding.spendtracker.data.SpendsTrackerDataSource
import net.simplifiedcoding.spendtracker.ui.SpendViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SpendViewModelTest : TestCase() {

    private lateinit var viewModel: SpendViewModel

//    here we define rules  which swaps background task and run all the task synchronously
    @get:Rule
    val instatTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    public override fun setUp() {
  super.setUp()
    val context = ApplicationProvider.getApplicationContext<Context>()
        val db = Room.inMemoryDatabaseBuilder(context,SpendsDatabase::class.java)
                     .allowMainThreadQueries().build()
        val dataSource = SpendsTrackerDataSource(db.getSpendDao())
        viewModel = SpendViewModel(dataSource)
    }
    //creating test case for viewmodel
    @Test
    fun testSpendViewModel(){
        viewModel.addSpend(170,"Bought some Flowers")
        viewModel.getLast20Spends()
       val result =  viewModel.last20SpendsLiveData.getOrAwaitValue().find {
            it.amount == 170 && it.description == "Bought some Flowers"
        }
         assertThat(result != null).isTrue()
    }

}