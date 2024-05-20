package com.example.convidados.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

abstract class GuestDataBase : RoomDatabase() {

    abstract fun guestDAO(): GuestDAO

    companion object {

        private lateinit var INSTANCE: GuestDataBase

        fun getDataBase(context: Context): GuestDataBase {
            if (!::INSTANCE.isInitialized) {
                synchronized(GuestDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context, GuestDataBase::class.java, "guestdb")
                        .addMigrations()
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                TODO("Not yet implemented")
            }
        }
    }
}