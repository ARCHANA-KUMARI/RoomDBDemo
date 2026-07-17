package com.example.roomdbdemo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactDao {
    @Insert
    suspend fun insertContact(contactDao: ContactDao)

    @Update
    suspend fun updateContact(contactDao: ContactDao)

    @Delete
    suspend fun deleteContact(contactDao: ContactDao)

    @Query("SELECT * FROM contact")
    fun getContacts(): LiveData<List<Contact>>
}