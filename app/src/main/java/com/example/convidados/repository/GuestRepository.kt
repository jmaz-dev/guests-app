package com.example.convidados.repository

import android.content.Context
import androidx.core.content.contentValuesOf
import com.example.convidados.constants.DataBaseConstants
import com.example.convidados.models.GuestModel

class GuestRepository private constructor(context: Context) {

    private val guestDataBase = GuestDataBase(context)
    private val guestTable: String = DataBaseConstants.GUEST.TABLE_NAME
    private val columnId: String = DataBaseConstants.GUEST.COLUMNS.ID
    private val columnName: String = DataBaseConstants.GUEST.COLUMNS.NAME
    private val columnPresence: String = DataBaseConstants.GUEST.COLUMNS.PRESENCE

    /*Singleton*/
    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            if (!Companion::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    fun postGuest(guest: GuestModel): Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val presence = if (guest.presence) 1 else 0

            val values = contentValuesOf()

            values.put(columnName, guest.name)
            values.put(columnPresence, presence)

            db.insert(guestTable, null, values)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun updateGuest(guest: GuestModel): Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            val presence = if (guest.presence) 1 else 0

            val values = contentValuesOf()
            values.put(columnName, guest.name)
            values.put(columnPresence, presence)

            /*set WHERE id = id ARGS*/
            val selection = columnId + " = ?"
            val args = arrayOf(guest.id.toString())

            db.update(
                guestTable,
                values,
                selection,
                args
            )
            true
        } catch (e: Exception) {
            false
        }
    }

    fun deleteGuest(id: Int): Boolean {
        return try {
            val db = guestDataBase.writableDatabase

            /*set WHERE id = id ARGS*/
            val selection = columnId + " = ?"
            val args = arrayOf(id.toString())

            db.delete(
                columnId,
                selection,
                args
            )
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getAllGuests(): List<GuestModel> {
        val list = mutableListOf<GuestModel>()
        try {

            val db = guestDataBase.readableDatabase

            val projection = arrayOf(
                columnId,
                columnName,
                columnPresence
            )

            val cursor = db.query(
                guestTable,
                projection,
                null,
                null,
                null,
                null,
                null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    val id =
                        cursor.getInt(cursor.getColumnIndexOrThrow(columnId))

                    val name =
                        cursor.getString(cursor.getColumnIndexOrThrow(columnName))

                    val presence =
                        cursor.getInt(cursor.getColumnIndexOrThrow(columnPresence))

                    list.add(GuestModel(id, name, presence == 1))
                }
            }

            cursor.close()
        } catch (e: Exception) {
            return list
        }
        return list
    }

    fun getPresents(): List<GuestModel> {
        val list = mutableListOf<GuestModel>()
        try {

            val db = guestDataBase.readableDatabase

            val projection = arrayOf(
                columnId,
                columnName,
                columnPresence
            )

            val selection = "$columnPresence = ?"
            val args = arrayOf("1")

            val cursor = db.query(
                guestTable,
                projection,
                selection,
                args,
                null,
                null,
                null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    val id =
                        cursor.getInt(cursor.getColumnIndexOrThrow(columnId))

                    val name =
                        cursor.getString(cursor.getColumnIndexOrThrow(columnName))

                    val presence =
                        cursor.getInt(cursor.getColumnIndexOrThrow(columnPresence))

                    list.add(GuestModel(id, name, presence == 1))
                }
            }

            cursor.close()
        } catch (e: Exception) {
            return list
        }
        return list
    }

    fun getAbsents(): List<GuestModel> {
        val list = mutableListOf<GuestModel>()
        try {

            val db = guestDataBase.readableDatabase

            val cursor =
                db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = 1", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    val id =
                        cursor.getInt(cursor.getColumnIndexOrThrow(columnId))

                    val name =
                        cursor.getString(cursor.getColumnIndexOrThrow(columnName))

                    val presence =
                        cursor.getInt(cursor.getColumnIndexOrThrow(columnPresence))

                    list.add(GuestModel(id, name, presence == 1))
                }
            }

            cursor.close()
        } catch (e: Exception) {
            return list
        }
        return list
    }


}