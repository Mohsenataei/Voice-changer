package com.example.voiceeffects.ui.repository

interface RecordDataSource {
    suspend fun readFromFile()
    suspend fun writeToFile()
}