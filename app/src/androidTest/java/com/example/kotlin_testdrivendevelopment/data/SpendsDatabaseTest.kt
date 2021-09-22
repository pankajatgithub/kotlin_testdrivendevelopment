package com.example.kotlin_testdrivendevelopment.data

import android.content.Context
import android.provider.ContactsContract
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import net.simplifiedcoding.spendtracker.data.Spend
import net.simplifiedcoding.spendtracker.data.SpendDao
import net.simplifiedcoding.spendtracker.data.SpendsDatabase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runner.manipulation.Ordering
import java.util.*

@RunWith(AndroidJUnit4::class)
class SpendsDatabaseTest : TestCase(){

    private lateinit var db: SpendsDatabase
    private lateinit var dao : SpendDao

    @Before
    public override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()
//   inMemoryDatabaseBuilder creates temporry database for testing
        db = Room.inMemoryDatabaseBuilder(context,SpendsDatabase::class.java).build()
        dao = db.getSpendDao()
    }
    @After
     fun closeDb(){
     db.close()
    }
//    Now we write test function
    @Test
    fun WriteandzReadSpend() = runBlocking{
        val date = Date()
    val spend = Spend(date,100,"Bought Somethng")
    dao.addSpend(spend)
    val  spends = dao.getLast20Spends()
    assertThat(spends.contains(spend)).isTrue()

    }

}