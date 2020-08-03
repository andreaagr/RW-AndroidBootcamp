package com.example.catapi.viewmodel

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.catapi.model.Cat
import com.example.catapi.repository.AppRepository
import com.example.catapi.room.CatDatabase
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class AddCatViewModelTest {

    private lateinit var viewModel: AddCatViewModel
    private lateinit var repo : AppRepository
    private lateinit var catDatabase : CatDatabase

    @Before
    fun setup(){
        catDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().context,CatDatabase::class.java).build()
        repo = AppRepository()
        viewModel = AddCatViewModel()
    }

    @Test
    fun emptyName_isCorrect() {
        val cat = Cat("","","","")
        val result = viewModel.emptyName(cat)
        Assert.assertEquals(true, result)
    }
}