package com.example.seniorprojectdc

import InsectDatabase
import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.seniorprojectdc.database.DAO
import com.example.seniorprojectdc.service_classes.Insect
import com.example.seniorprojectdc.service_classes.getCurrentDateString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertEquals
import kotlinx.coroutines.flow.first



@RunWith(AndroidJUnit4::class)
class DatabaseTests {
    private lateinit var database: InsectDatabase
    private lateinit var test_dao: DAO

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext<Context>(),
            InsectDatabase::class.java).allowMainThreadQueries().build()

        test_dao = database.insectDao()
    }

    @After
    fun cleanup() {
        database.close()
    }

    @Test
    fun insertAndReadBack() = runBlocking {
        val testInsect = Insect(
            insectName = "Fly",
            date = getCurrentDateString(),
            imageUri = "",
            notes = "",
            nickname = ""
        )
        test_dao.insert(testInsect)

        val insects = test_dao.getAllInsects().first()
        assertEquals(1, insects.size)
        assertEquals("Fly", insects[0].insectName)
    }

    @Test
    fun insertAndDelete() = runBlocking {
        var testInsect = Insect(
            insectName = "Beetle",
            date = getCurrentDateString(),
            imageUri = "",
            notes = "",
            nickname = ""
        )
        test_dao.insert(testInsect)

        var insects = test_dao.getAllInsects().first()
        assertEquals(1, insects.size)

        //The Insect needs to be re-queried from the database in order
        //to assign it an ID
        //This is how it is used in the app
        testInsect = test_dao.getAllInsects().first()[0]

        test_dao.delete(testInsect)
        insects = test_dao.getAllInsects().first()
        assertEquals(0, insects.size)
    }

    @Test
    fun insertAndEdit() = runBlocking {
        var testInsect = Insect(
            insectName = "Caterpillar",
            date = getCurrentDateString(),
            imageUri = "",
            notes = "Initial Note",
            nickname = ""
        )
        test_dao.insert(testInsect)

        var insects = test_dao.getAllInsects().first()
        assertEquals(1, insects.size)
        assertEquals("Caterpillar", insects[0].insectName)
        assertEquals("Initial Note", insects[0].notes)

        //The Insect needs to be re-queried from the database, before editing,
        // in order to assign it an ID
        //This is how it is used in the app
        testInsect = test_dao.getAllInsects().first()[0]

        testInsect.notes = "New Note"
        testInsect.insectName = "Butterfly"
        test_dao.updateInsect(testInsect)

        insects = test_dao.getAllInsects().first()
        assertEquals(1, insects.size)
        assertEquals("Butterfly", insects[0].insectName)
        assertEquals("New Note", insects[0].notes)
    }
}