package com.example.roomdbdemo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Contact::class], version = 2)
@TypeConverters(com.example.roomdbdemo.TypeConverters::class)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao


    companion object {
        @Volatile
        private var INSTANCE: ContactDatabase? = null

        val migration_Ver_1_To_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE contact ADD COLUMN createdDate DATE INTEGER NOT NULL DEFAULT 0")
            }
        }

        fun getDataBase(context: Context): ContactDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(context, ContactDatabase::class.java, "contactDB").addMigrations(migration_Ver_1_To_2)
                            .build()
                }
            }
            return INSTANCE!!
        }
    }

}