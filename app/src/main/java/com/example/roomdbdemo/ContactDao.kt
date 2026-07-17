package com.example.roomdbdemo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactDao {
    @Insert
    fun insertContact(contactDao: ContactDao)

    @Update
    fun updateContact(contactDao: ContactDao)

    @Delete
    fun deleteContact(contactDao: ContactDao)

    @Query("SELECT * FROM contact")
    fun getContacts(): List<Contact>
}