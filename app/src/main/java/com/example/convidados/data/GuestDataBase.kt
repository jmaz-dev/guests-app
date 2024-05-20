package com.example.convidados.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.convidados.constants.DataBaseConstants

//abstract class GuestDataBase : RoomDatabase() {
//
//    abstract fun guestDAO(): GuestDAO
//
//    companion object {
//
//        private lateinit var INSTANCE: GuestDataBase
//
//        fun getDataBase(context: Context): GuestDataBase {
//            if (!::INSTANCE.isInitialized) {
//                synchronized(GuestDataBase::class) {
//                    INSTANCE = Room.databaseBuilder(context, GuestDataBase::class.java, "guestdb")
//                        .addMigrations()
//                        .allowMainThreadQueries()
//                        .build()
//                }
//            }
//            return INSTANCE
//        }
//
//        private val MIGRATION_1_2 = object : Migration(1, 2) {
//            override fun migrate(db: SupportSQLiteDatabase) {
//                TODO("Not yet implemented")
//            }
//        }
//    }
//}

class GuestDataBase(
    context: Context,
) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object {
        private val NAME = "guestdb"
        private val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE " + DataBaseConstants.GUEST.TABLE_NAME + "(" +
                    DataBaseConstants.GUEST.COLUMNS.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DataBaseConstants.GUEST.COLUMNS.NAME + " TEXT," +
                    DataBaseConstants.GUEST.COLUMNS.PRESENCE + " INTEGER)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}
