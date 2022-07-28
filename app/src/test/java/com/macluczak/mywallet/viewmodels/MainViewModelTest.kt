package com.macluczak.mywallet.viewmodels

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test


class MainViewModelTest {

    @Rule @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `validateTitle is true`() {
        val application: Application = mockk(relaxed = true)
        val viewModel=  MainViewModel(application)
        val result = viewModel.validateTitle("title1")
        assertEquals(true, result)
    }

    @Test
    fun `validateTitle is false`() {
        val application: Application = mockk(relaxed = true)
        val viewModel=  MainViewModel(application)
        val result = viewModel.validateTitle("")
        assertEquals(result, false)
    }

    @Test
    fun `validateDescription is true`() {
        val application: Application = mockk(relaxed = true)
        val viewModel=  MainViewModel(application)
        val result = viewModel.validateDescription("description1")
        assertEquals(true, result)
    }

    @Test
    fun `validateDescription is false`() {
        val application: Application = mockk(relaxed = true)
        val viewModel=  MainViewModel(application)
        val result = viewModel.validateDescription("")
        assertEquals(result, false)
    }
}